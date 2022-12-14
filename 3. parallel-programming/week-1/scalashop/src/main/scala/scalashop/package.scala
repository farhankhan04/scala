import java.util.concurrent._
import scala.util.DynamicVariable

import org.scalameter._

package object scalashop extends BoxBlurKernelInterface {

  /** The value of every pixel is represented as a 32 bit integer. */
  type RGBA = Int

  /** Returns the red component. */
  def red(c: RGBA): Int = (0xff000000 & c) >>> 24

  /** Returns the green component. */
  def green(c: RGBA): Int = (0x00ff0000 & c) >>> 16

  /** Returns the blue component. */
  def blue(c: RGBA): Int = (0x0000ff00 & c) >>> 8

  /** Returns the alpha component. */
  def alpha(c: RGBA): Int = (0x000000ff & c) >>> 0

  /** Used to create an RGBA value from separate components. */
  def rgba(r: Int, g: Int, b: Int, a: Int): RGBA = {
    (r << 24) | (g << 16) | (b << 8) | (a << 0)
  }

  /** Restricts the integer into the specified range. */
  def clamp(v: Int, min: Int, max: Int): Int = {
    if (v < min) min
    else if (v > max) max
    else v
  }

  /** Image is a two-dimensional matrix of pixel values. */
  class Img(val width: Int, val height: Int, private val data: Array[RGBA]) {
    def this(w: Int, h: Int) = this(w, h, new Array(w * h))
    def apply(x: Int, y: Int): RGBA = data(y * width + x)
    def update(x: Int, y: Int, c: RGBA): Unit = data(y * width + x) = c
  }

  /** Computes the blurred RGBA value of a single pixel of the input image. */
  def boxBlurKernel(src: Img, x: Int, y: Int, radius: Int): RGBA = {

    // TODO implement using while loops
    /*val xMin = clamp(x-radius, 0, src.width-1)
    val xMax = clamp(x+radius, 0, src.width-1)
    val yMin = clamp(y-radius, 0, src.height-1)
    val yMax = clamp(y+radius, 0, src.height-1)
    var x_cur = xMin
    var y_cur = yMin
    var red_acc = 0;
    var green_acc = 0;
    var blue_acc = 0;
    var alpha_acc = 0;
    val no_of_pixels = (xMax-xMin+1)*(yMax-yMin+1)
    while(x_cur <= xMax){
      y_cur = yMin
      while(y_cur <= yMax) {
        red_acc += red(src(x_cur, y_cur))
        green_acc += green(src(x_cur, y_cur))
        blue_acc += blue(src(x_cur, y_cur))
        alpha_acc += alpha(src(x_cur, y_cur))
        y_cur+=1
      }
      x_cur+=1
    }
    rgba(red_acc/no_of_pixels, green_acc/no_of_pixels, blue_acc/no_of_pixels, alpha_acc/no_of_pixels)*/
    val minX = clamp(x - radius, 0, src.width - 1)
    val maxX = clamp(x + radius, 0, src.width - 1)
    val minY = clamp(y - radius, 0, src.height - 1)
    val maxY = clamp(y + radius, 0, src.height - 1)
    var accX = minX
    var accY = minY
    var pixelProcessed = 0
    var accR = 0
    var accG = 0
    var accB = 0
    var accA = 0
    while(accX <= maxX) {
      while(accY <= maxY) {
        val pixel = src.apply(accX, accY)
        accR += red(pixel)
        accG += green(pixel)
        accB += blue(pixel)
        accA += alpha(pixel)
        pixelProcessed += 1
        accY += 1
      }
      accX += 1
      accY = minY
    }
    rgba(accR / pixelProcessed, accG / pixelProcessed, accB / pixelProcessed, accA / pixelProcessed)
  }

  val forkJoinPool = new ForkJoinPool

  abstract class TaskScheduler {
    def schedule[T](body: => T): ForkJoinTask[T]
    def parallel[A, B](taskA: => A, taskB: => B): (A, B) = {
      val right = task {
        taskB
      }
      val left = taskA
      (left, right.join())
    }
  }

  class DefaultTaskScheduler extends TaskScheduler {
    def schedule[T](body: => T): ForkJoinTask[T] = {
      val t = new RecursiveTask[T] {
        def compute = body
      }
      Thread.currentThread match {
        case wt: ForkJoinWorkerThread =>
          t.fork()
        case _ =>
          forkJoinPool.execute(t)
      }
      t
    }
  }

  val scheduler =
    new DynamicVariable[TaskScheduler](new DefaultTaskScheduler)

  def task[T](body: => T): ForkJoinTask[T] = {
    scheduler.value.schedule(body)
  }

  def parallel[A, B](taskA: => A, taskB: => B): (A, B) = {
    scheduler.value.parallel(taskA, taskB)
  }

  def parallel[A, B, C, D](taskA: => A, taskB: => B, taskC: => C, taskD: => D): (A, B, C, D) = {
    val ta = task { taskA }
    val tb = task { taskB }
    val tc = task { taskC }
    val td = taskD
    (ta.join(), tb.join(), tc.join(), td)
  }

  // Workaround Dotty's handling of the existential type KeyValue
  implicit def keyValueCoerce[T](kv: (Key[T], T)): KeyValue = {
    kv.asInstanceOf[KeyValue]
  }
}
