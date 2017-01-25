package files

import akka.persistence.PersistentActor

/**
  * Created by meisam on 10/09/2016.
  */

case class DevicePosition(imei: String, longitude: Float, latitude: Float, timestamp: Long)

case class TrackingEvent(devicePosition: DevicePosition)
case class TrackingCommand(devicePosition: DevicePosition)

case class TrackingState(events: List[DevicePosition] = Nil) {
  def updated(trackingCommand: TrackingCommand) = copy(trackingCommand.devicePosition :: events)
}

class TrackingPersistentActor extends PersistentActor {
  override def persistenceId: String = "TrackingActor-Persistent"

  var state = TrackingState()

  override def receiveRecover: Receive = ???

  override def receiveCommand: Receive = ???
}
