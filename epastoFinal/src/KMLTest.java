import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

//import de.micromata.opengis.kml.v_2_2_0.AltitudeMode;
//import de.micromata.opengis.kml.v_2_2_0.Boundary;
//import de.micromata.opengis.kml.v_2_2_0.ColorMode;
//import de.micromata.opengis.kml.v_2_2_0.Coordinate;
//import de.micromata.opengis.kml.v_2_2_0.Document;
//import de.micromata.opengis.kml.v_2_2_0.Feature;
//import de.micromata.opengis.kml.v_2_2_0.Folder;
//import de.micromata.opengis.kml.v_2_2_0.Geometry;
//import de.micromata.opengis.kml.v_2_2_0.GroundOverlay;
//import de.micromata.opengis.kml.v_2_2_0.Kml;
//import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
//import de.micromata.opengis.kml.v_2_2_0.LatLonBox;
//import de.micromata.opengis.kml.v_2_2_0.LineString;
//import de.micromata.opengis.kml.v_2_2_0.LinearRing;
//import de.micromata.opengis.kml.v_2_2_0.ListItemType;
//import de.micromata.opengis.kml.v_2_2_0.LookAt;
//import de.micromata.opengis.kml.v_2_2_0.MultiGeometry;
//import de.micromata.opengis.kml.v_2_2_0.Placemark;
//import de.micromata.opengis.kml.v_2_2_0.Point;
//import de.micromata.opengis.kml.v_2_2_0.Polygon;
//import de.micromata.opengis.kml.v_2_2_0.Style;













import de.micromata.opengis.kml.v_2_2_0.*;


public class KMLTest {
	
    private AppliController _appliController;
	
	private ArrayList<Integer> numPoint = new ArrayList<Integer>();
	private ArrayList<Double> latitude = new ArrayList<Double>();
	private ArrayList<Double> longitude = new ArrayList<Double>();
	private ArrayList<Double> altitude = new ArrayList<Double>();
	
	private int nbrPoint = 0;
	
	public void kmlReader(String nomFichier) throws FileNotFoundException
	{

			try {
			final Kml kml = Kml.unmarshal(new File(nomFichier), false);
			final Document document = (Document)kml.getFeature();
	        System.out.println(document.getName());
	        List<Feature> list = document.getFeature();
	        
	        for(Object object : list){
	            Folder folder = (Folder)object;
	            System.out.println(folder.getName());
	            
	            List<Feature> tg = folder.getFeature();
	            	            
	            for(Object ftg : tg){
	                Placemark placemark = (Placemark) ftg;
	                
	                System.out.println(placemark.getName());
	                final Polygon polygon = (Polygon)placemark.getGeometry();
	                
	                final Boundary boundary = (Boundary)polygon.getOuterBoundaryIs();
	
	                LinearRing  linearRing  = boundary.getLinearRing();
	    			
	                List<Coordinate> coordinates = linearRing.getCoordinates();
	    			
	    			for (Coordinate coordinate : coordinates) {
	    				
	    				numPoint.add(coordinates.indexOf(coordinate));
	    				latitude.add(coordinate.getLatitude());
	    				longitude.add(coordinate.getLongitude());
	    				altitude.add(coordinate.getAltitude());
			
//	    				numPoint = coordinates.indexOf(coordinate);
//	    				latitude = coordinate.getLatitude();
//	    				longitude = coordinate.getLongitude();
//	    				altitude = coordinate.getAltitude();
	    				
	    				/*System.out.println("Point numéro: " + numPoint);
	    				System.out.println("Latitude: " + latitude);
	    				System.out.println("Longitude: " + longitude);
	    				System.out.println("Altitude: " + altitude);*/
	    				
	    				//_appliController.getAppliModel().insererCoord(numPoint, latitude, longitude, altitude);
	    			}  
	    			
	    			nbrPoint = numPoint.size();
	    			System.out.println("Nombre de point :"+nbrPoint);
	            }
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * HelloKML Sample project
	 */
	/*public static void main(String[] args) {
	
		KMLTest kml = new KMLTest();
		try {
			kml.kmlReader("Epasto.kml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
//		kml.parseKml();
		
//		try {
//			final Kml kml = new Kml();
//			kml.createAndSetPlacemark().withName("London, UK").withOpen(Boolean.TRUE).createAndSetPoint().addToCoordinates(-0.126236, 51.500152);
//			kml.marshal(new File("HelloKml.kml"));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	//}

	public ArrayList<Integer> getNumPoint() {
		return numPoint;
	}

	public void setNumPoint(ArrayList<Integer> numPoint) {
		this.numPoint = numPoint;
	}

	public ArrayList<Double> getLatitude() {
		return latitude;
	}

	public void setLatitude(ArrayList<Double> latitude) {
		this.latitude = latitude;
	}

	public ArrayList<Double> getLongitude() {
		return longitude;
	}

	public void setLongitude(ArrayList<Double> longitude) {
		this.longitude = longitude;
	}

	public ArrayList<Double> getAltitude() {
		return altitude;
	}

	public void setAltitude(ArrayList<Double> altitude) {
		this.altitude = altitude;
	}

	public int getNbrPoint() {
		return nbrPoint;
	}

	public void setNbrPoint(int nbrPoint) {
		this.nbrPoint = nbrPoint;
	}
}
