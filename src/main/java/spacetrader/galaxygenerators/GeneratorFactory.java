/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spacetrader.galaxygenerators;

import java.util.HashMap;
import spacetrader.game_model.GameModel;
/**
 *
 * @author Tyler Allen <tallen40@gatech.edu>
 */
public class GeneratorFactory {
    HashMap<String,generate> genMap;
    HashMap<String,Class<?>> classMap;
    private interface generate{
        Object generate();
    }
    public GeneratorFactory(){
        genMap=new HashMap();
        classMap=new HashMap();
        genMap.put("EllipticalGalaxyGenerator", ()->{
            return new EllipticalGalaxyGenerator();
        });
        classMap.put("EllipticalGalaxyGenerator",new EllipticalGalaxyGenerator().getClass());
        genMap.put("SimpleStarSystemGenerator", ()->{
            return new SimpleStarSystemGenerator();
        });
        classMap.put("SimpleStarSystemGenerator",new SimpleStarSystemGenerator().getClass());

        genMap.put("SimplePlanetGenerator", ()->{
            return new SimplePlanetGenerator();
        });
        classMap.put("SimplePlanetGenerator",new SimplePlanetGenerator().getClass());

        genMap.put("JumpPointsGenerator", ()->{
            return new JumpPointsGenerator();
        });
        classMap.put("JumpPointsGenerator",new JumpPointsGenerator().getClass());

    }
    public Object getGenerator(String type){
        return genMap.get(type).generate();
    }
    public Class<?> getGeneratorClass(String type){
        return classMap.get(type);
    }
}
