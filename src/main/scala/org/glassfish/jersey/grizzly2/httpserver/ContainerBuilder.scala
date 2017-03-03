package org.glassfish.jersey.grizzly2.httpserver

import org.glassfish.jersey.server.ResourceConfig

object ContainerBuilder {
      def buildContainer(config : ResourceConfig) = {
          new GrizzlyHttpContainer(config)
      }
}