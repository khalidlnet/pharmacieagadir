package controller;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.*;

import org.apache.axis2.AxisFault;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.stereotype.Component;






import ws.* ;
import ws.InsertWebStub.InsertAll;
import ws.PharmacieWebSStub.*;


@ManagedBean
@SessionScoped
@Component
public class PharmacieController {
	
	private String ville;
	private Double latitude;
	private LatLng coord2 = null;
	private MapModel advancedModel;
	List<Pharmacie> listpharmacie=new ArrayList<Pharmacie>();
	List<LatLng> listlng =new ArrayList<LatLng>();
    public MapModel getAdvancedModel() {
		return advancedModel;
	}
    
    
    
    
    
    public PharmacieController() {
		super();
	}



	public void setAdvancedModel(MapModel advancedModel) {
		this.advancedModel = advancedModel;
	}

	public Marker getMarker() {
		return marker;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}


	private Marker marker;
	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}


	private Double longitude;
	
	
	
	private Pharmacie[] l ;
	
	public Pharmacie[] getL() {
		return l;
	}

	public void setL(Pharmacie[] l) {
		this.l = l;
	}
	
	public void findAll() {
		System.out.println("dsds");
		try {
			PharmacieWebSStub pss = new PharmacieWebSStub();
			this.l = pss.findAll(new FindAll()).get_return();
		} catch (Exception e) {
			e.printStackTrace();
		}
		advancedModel = new DefaultMapModel();
		
		for(PharmacieWebSStub.Pharmacie ph : l) {
		      LatLng coord = new LatLng(Double.parseDouble(ph.getLt()),Double.parseDouble(ph.getLg()));
		      listlng.add(coord);
		      if(Integer.parseInt(ph.getDistance()) == 2){
		    	  coord2 = new LatLng(Double.parseDouble(ph.getLt()),Double.parseDouble(ph.getLg()));
		    	  
		      }
		      
		}
		
		
	     for(LatLng lgt: listlng)
	       {
	        advancedModel.addOverlay(new Marker(lgt, "khalid", "images/excel.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
	       
	        
	      }
	         
	        advancedModel.addOverlay(new Marker(coord2, "khalid", "images/excel.png", "http://maps.google.com/mapfiles/ms/micons/pink-dot.png"));

    	}
		
	
    	
		
		
		
	
	
	@PostConstruct
	public void init(){

		
	}
	
	
	
	public void onMarkerSelect(OverlaySelectEvent event) {
        marker = (Marker) event.getOverlay();
    }
	
	
	
	public List<LatLng> getListlng() {
		return listlng;
	}

	public void setListlng(List<LatLng> listlng) {
		this.listlng = listlng;
	}

	
	
	public void insertAll() {
		try {
			InsertWebStub is = new InsertWebStub();
			is.insertAll(new InsertAll());		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}
	

	
	
	
}

