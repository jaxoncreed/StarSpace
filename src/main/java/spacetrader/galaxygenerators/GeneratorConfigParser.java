package spacetrader.galaxygenerators;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.xml.sax.*;
import java.lang.reflect.Method;
import org.w3c.dom.*;

/**
 * You may 
 */
public class GeneratorConfigParser {

	private Document _doc;
    private String _filename;
	private List<GalaxyGenerator> _galaxyGenerators;
	private List<StarSystemGenerator> _starSystemGenerators;
	private List<PlanetGenerator> _planetGenerators;
    private List<JumpPointsGenerator> _jumpPointsGenerators;

	public GeneratorConfigParser(String filename) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		dbf.setIgnoringComments(true);
		dbf.setIgnoringElementContentWhitespace(true);
		dbf.setCoalescing(true);
		dbf.setExpandEntityReferences(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		_doc = db.parse(new File(this.getClass().getResource(filename).toURI()));
        _filename = filename;

		_galaxyGenerators = new ArrayList();
		_starSystemGenerators = new ArrayList();
		_planetGenerators = new ArrayList();
        _jumpPointsGenerators = new ArrayList();
	}

	public void createGenerators() throws Exception {

		List<Element> genTags = getChildElements(_doc.getDocumentElement());
        
		for (Element genTag : genTags) {
			// get the generator type
			String type = genTag.getTagName();
            Object gen = new GeneratorFactory().getGenerator(type);
            Class<?> genClass = new GeneratorFactory().getGeneratorClass(type);
			// read the properties and set them
			List<Element> propTags = getChildElements(genTag);
			for (Element propTag : propTags) {
				String propName = propTag.getTagName();
				String propValue = propTag.getTextContent().trim();
                if (propValue == null || propValue.equals("")) {
                    System.out.println("YO FOOL: Element " + propName + " given "
                        + "no data. Fix the XML file " + _filename);
                    System.exit(1);
                }
				// get the cast type for the argument
				Class<?> castType = guessCastType(propValue);
				Object prop = null;
				if (castType.isAssignableFrom(Double.class)) {
					prop = new Double(Double.parseDouble(propValue));
				} else if (castType.isAssignableFrom(Integer.class)) {
					prop = new Integer(Integer.parseInt(propValue));
				} else if (castType.isAssignableFrom(String.class)) {
					prop = propValue;
				} else if (castType.isAssignableFrom(Boolean.class)) {
					prop = new Boolean(Boolean.parseBoolean(propValue));
				}
				// get the Method
                Method method = null;
                String methodName = "set" + camelify(propName);
                boolean autoboxedToDouble = false;
//                System.out.println("\n" + type + ":");
//                Method[] methods = genClass.getMethods();
                
//                for (Method thisMethod : methods) {
//                    System.out.print(thisMethod.getName() + "(");
//                    Class<?>[] params = thisMethod.getParameterTypes();
//                    for (Class<?> param : params) {
//                        System.out.print(param.getName() + ",");
//                    }
//                    System.out.println(")");
//                }
                try {
                    method = genClass.getMethod(methodName, castType);
                } catch (NoSuchMethodException e) {
                    // if an Integer parameter type was specified but no such method
                    // on integers exist, look for a method that takes in a Double
                    if (castType.isAssignableFrom(Integer.class)) {
                        try {
//                            System.out.println("Trying to make it a double.");
                            method = genClass.getMethod(methodName, Double.class);
                            autoboxedToDouble = true;
                            
                        } catch  (NoSuchMethodException ee) {}
                    }
                    
                    if (!autoboxedToDouble) {
                        System.out.println("YO FOOL: Method " + methodName +
                             " on an argument of type " + castType.getName() + 
                             " does not exist in the class " + type + 
                             "; fix the XML file " + _filename);
                             System.exit(1);
                    }
                } 
                
                try {
//                    System.out.print("Attempting to invoke " + methodName + "(");
//                    Class<?>[] params = method.getParameterTypes();
//                    for (Class<?> param : params) {
//                        System.out.print(param.getName() + ",");
//                    }
//                    System.out.println(")");
                    if (autoboxedToDouble) {
//                        System.out.println("above invocation autoboxed to double");
                        method.invoke(gen, new Double(((Integer) prop).doubleValue()));
                    } else {
//                        System.out.println("above invocation kept");
                        method.invoke(gen, prop); 
                    }
                } catch (Exception e) {
                    System.out.println("YO FOOL: Invoking " + methodName +  
                        " on " + propValue + " failed for some reason." + 
                        "\nException thrown: " + e.getClass().getName() + 
                        "\nMessage: " + e.getMessage());
                    System.exit(1);
                }					
			}

			// add gen to the appropriate list
			if (gen instanceof GalaxyGenerator) {
				_galaxyGenerators.add((GalaxyGenerator) gen);

			} else if (gen instanceof StarSystemGenerator) {
				_starSystemGenerators.add((StarSystemGenerator) gen);

			} else if (gen instanceof PlanetGenerator) {
				_planetGenerators.add((PlanetGenerator) gen);

			} else if (gen instanceof JumpPointsGenerator) {
                _jumpPointsGenerators.add((JumpPointsGenerator) gen);
                
            } else {
				throw new RuntimeException("YO FOOL: I don't know how you managed"
                    + " to get this far in the code and not run in to any other errors."
                    + " I don't know what's wrong with your input. It might be that"
                    + " your computer is infested with goblins. Or something.");
			}
		}
	}

	public List<GalaxyGenerator> getGalaxyGenerators() {
		return _galaxyGenerators;
	}

	public List<StarSystemGenerator> getStarSystemGenerators() {
		return _starSystemGenerators;
	}

	public List<PlanetGenerator> getPlanetGenerators() {
		return _planetGenerators;
	}
    
    public List<JumpPointsGenerator> getJumpPointsGenerators() {
        return _jumpPointsGenerators;
    }

	private String camelify(String str) {
		StringBuilder builder = new StringBuilder(str);
		builder.replace(0, 1, str.substring(0, 1).toUpperCase());
		return builder.toString();
	}


	private Class<?> guessCastType(String value) {

		// parse ints
		try {
			Integer.parseInt(value);
			return Integer.class;
		} catch (NumberFormatException e) {}

		// parse doubles
		try {
			Double.parseDouble(value);
			return Double.class;
		} catch (NumberFormatException e) {}

		// parse booleans
		if (value.toLowerCase().equals("true")
			|| value.toLowerCase().equals("false")) {
			return Boolean.class;
		}

		return String.class;
	}

	private List<Element> getChildElements(Node node) {
		List<Element> nodesToReturn = new ArrayList();
		NodeList childNodes = node.getChildNodes();
		int size = childNodes.getLength();
		for (int i = 0; i < size; i++) {
			Node thisNode = childNodes.item(i);
            if (thisNode instanceof Element) {
                nodesToReturn.add((Element) thisNode);
            }
		}
		return nodesToReturn;
	}

	private String convertToFileURL(String filename) {
		String path = new File(filename).getAbsolutePath();
		if (File.separatorChar != '/') {
			path = path.replace(File.separatorChar, '/');
		}
		if (!path.startsWith("/")) {
			path = "/" + path;
		}

		return "file:" + path;
	}


