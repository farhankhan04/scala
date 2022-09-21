package week4

abstract class Gates extends Simulation {
  def InverterDelay: Int
  def AndGateDelay: Int
  def OrGateDelay: Int

  class Wire {
    private var sigVal = false
    private var actions: List[Action] = List()
    def getSignal: Boolean = sigVal
    def setSignal(s:Boolean): Unit = {
      if(s!=sigVal){
        sigVal = s
        actions foreach(_())
      }
    }
    def addAction(a: Action): Unit = {
      actions = a :: actions
      a()
    }
  }

  def inverter(input: Wire, output: Wire): Unit = {
    def invertAction: () => Unit = () => {
      val inputSig = input.getSignal
      afterDelay(InverterDelay) {
        output setSignal !inputSig
      }
    }
    input.addAction (invertAction)
  }

  def orGate(in1: Wire, in2:Wire, output: Wire): Unit = {
    def orAction: () => Unit = () => {
      val inSig1 = in1.getSignal
      val inSig2 = in2.getSignal
      afterDelay(OrGateDelay){
        output setSignal inSig1 | inSig2
      }
    }
    in1 addAction orAction
    in2 addAction orAction
  }

  def andGate(in1: Wire, in2:Wire, output: Wire): Unit = {
    def orAction: () => Unit = () => {
      val inSig1 = in1.getSignal
      val inSig2 = in2.getSignal
      afterDelay(OrGateDelay){
        output setSignal inSig1 & inSig2
      }
    }

    in1 addAction(orAction)
    in2 addAction(orAction)
  }

  def probe(name: String, wire: Wire): Unit = {
    def probeAction = () => {
      println(name+" "+currentTime+" new-value "+wire.getSignal)
    }
    wire addAction probeAction
  }

}
