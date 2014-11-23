I would recommend that you start reading the comments in generator_config.xml, since 
this will be your (only) entry point for configuring the galaxy generators. Otherwise, 
documentation on the generators can be found as javadocs in the classes:

> GalaxyGenerator
> StarSystemGenerator
> PlanetGenerator
> EllipticalGalaxyGenerator
> SimpleStarSystemGenerator
> SimplePlanetGenerator


MISCELLANEA
> In order to configure how StarSystems and Planets are named, ctrl + f
for the string "SET PROPERTIES" in the classes EllipticalGalaxyGenerator and
SimpleStarSystemGenerator, respectively. This solution was kludged in because we don't have a
good way of naming planets and star systems at present.