import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import javax.swing.table.DefaultTableModel;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.LocalResponseCache;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import Maps.RoutePainter;
import Maps.SelectionAdapter;
import Maps.SelectionPainter;
import Maps.MyWaypoint;
import Maps.FancyWaypointRenderer;

public class Maps {

    private AppliController _appliController;
	private KMLTest kmlReader;
	private JInternalFrame intfram;
	private final JXMapViewer mapViewer = new JXMapViewer();

	public Maps(AppliController appliController) {
		// TODO Auto-generated constructor stub
		this._appliController = appliController;
		this.kmlReader  = new KMLTest();
		//afficherMaps("PratAlbis.kml");
	}
	
	public void afficherMaps(String path){
		
		//JXMapViewer mapViewer = new JXMapViewer();
		try {
			kmlReader.kmlReader(path);
			//kmlReader.kmlReader("Epasto.kml");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Create a TileFactoryInfo for OpenStreetMap
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		tileFactory.setThreadPoolSize(8);
	
		// Setup local file cache
		File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		LocalResponseCache.installResponseCache(info.getBaseURL(), cacheDir, false);
	
		// Setup JXMapViewer
		mapViewer.setTileFactory(tileFactory);
		

        //location of Java
        final GeoPosition pratAlbis = new GeoPosition( 42.918907, 1.5802651); 

		// Set the focus
		//mapViewer.setZoom(7);
		//mapViewer.setAddressLocation(pratAlbis);
		
		// Add interactions
		MouseInputListener mia = new PanMouseInputListener(mapViewer);
		mapViewer.addMouseListener(mia);
		mapViewer.addMouseMotionListener(mia);

		mapViewer.addMouseListener(new CenterMapListener(mapViewer));
		
		mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
		
		mapViewer.addKeyListener(new PanKeyListener(mapViewer));
		
		///**** 25/11 *////
		
		
		
		
		//*** fin 25/11 ***///
		
		// Add a selection painter
		SelectionAdapter sa = new SelectionAdapter(mapViewer); 
		SelectionPainter sp = new SelectionPainter(sa); 
		mapViewer.addMouseListener(sa); 
		mapViewer.addMouseMotionListener(sa); 
		mapViewer.setOverlayPainter(sp);
		
        //coord = _appliModel.getCoord();
        List<GeoPosition>  point = new ArrayList<GeoPosition>();
        
        List<GeoPosition> track = new ArrayList<GeoPosition>();
        
        //Integer i = new Integer(0);
        for (int i = 0; i < kmlReader.getNbrPoint(); i++){
        	//System.out.println("For geop:"+i);
        	//System.out.println("Max for:" +kmlReader.getNbrPoint());
        	//System.out.println("Taille array point:"+point.size());
        	point.add(new GeoPosition(kmlReader.getLatitude().get(i),kmlReader.getLongitude().get(i)));
        	//System.out.println("Valeur array point:"+point.get(i-1));
        	track = point;
        	//System.out.println("Valeur track:"+track.get(i-1));
        }
        
		RoutePainter routePainter = new RoutePainter(track);

		mapViewer.setAddressLocation(pratAlbis);
		mapViewer.setZoom(7);
		//mapViewer.zoomToBestFit(new HashSet<GeoPosition>(track), 1);
    	

		//List<DefaultWaypoint> tabCoord = new ArrayList<DefaultWaypoint>();
		List<MyWaypoint> tabCoord = new ArrayList<MyWaypoint>();
		//System.out.println("Taille Arraylist tabcoord:"+tabCoord.size());

		for (int i = 1; i <= kmlReader.getNbrPoint()-1; i++){
			//tabCoord.add((new DefaultWaypoint(point.get(i-1))));
			tabCoord.add(new MyWaypoint(Integer.toString(i), Color.ORANGE,(point.get(i-1))));
		}
		

		Set<MyWaypoint> waypoints = new HashSet<MyWaypoint>(tabCoord);
		//Set<Waypoint> waypoints = new HashSet<Waypoint>(tabCoord);
		//System.out.println();
		
		// Create a waypoint painter that takes all the waypoints
//		WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
//		waypointPainter.setWaypoints(waypoints);
		// Create a waypoint painter that takes all the waypoints
		WaypointPainter<MyWaypoint> waypointPainter = new WaypointPainter<MyWaypoint>();
		waypointPainter.setWaypoints(waypoints);
		waypointPainter.setRenderer(new FancyWaypointRenderer());
		
		mapViewer.setOverlayPainter(waypointPainter);

		// Create a compound painter that uses both the route-painter and the waypoint-painter
		List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
		painters.add(routePainter);
		painters.add(waypointPainter);
		
		CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
		mapViewer.setOverlayPainter(painter);

		//mapViewer.Set
		// Display the viewer in a JFrame
		//JPanel panel = new JPanel();
		//label.getContentPane()
		//intfram = new JInternalFrame("JXMapviewer2 Example 2");
		//intfram.getContentPane().add(mapViewer);
		/*intfram.setSize(800, 600);
		intfram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		intfram.setVisible(true);*/
		
		/*JFrame frame = new JFrame("JXMapviewer2 Example 2");
		frame.getContentPane().add(mapViewer);
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);*/
	}	
	
	public void creerWaypoint(){
		
		
		
	}

	public JInternalFrame getIntfram() {
		return intfram;
	}

	public void setIntfram(JInternalFrame intfram) {
		this.intfram = intfram;
	}

	public JXMapViewer getMapViewer() {
		return mapViewer;
	}
	
	public void start()
	{
		mapViewer.setVisible(true);
	}
	
	public void stop()
	{
		mapViewer.setVisible(false);
	}
}
