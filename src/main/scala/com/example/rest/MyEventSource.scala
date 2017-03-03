package com.example.rest

import javax.ws.rs._
import javax.inject.Inject
import org.glassfish.jersey.media.sse.SseFeature
import org.glassfish.jersey.media.sse.EventOutput
import org.glassfish.jersey.media.sse.OutboundEvent

@Path("events")
class MyEventSource @Inject() (private val repo: PersonRepository) {
  @GET
  @Produces(Array(SseFeature.SERVER_SENT_EVENTS))
  def getEvents(): EventOutput = {
    val eventOutput = new EventOutput()
    new Thread(new Runnable {
      override def run(): Unit = {
        try {
          for (p <- repo.getAll()) {
            Thread.sleep(250)
            val eventBuilder = new OutboundEvent.Builder()
            //eventBuilder.name("message-to-client")
            val msg = "Hello, " + p.firstName + " " + p.lastName + "!"
            eventBuilder.data(classOf[String], msg)
            val event = eventBuilder.build()
            eventOutput.write(event)
          }
        } catch {
          case e: Exception => e.printStackTrace()
        } finally {
          try {
            println("Closing output")
            eventOutput.close()
          } catch {

            case e: Exception => println("Error closing output")
          }
        }
      }
    }).start()
    eventOutput
  }
}