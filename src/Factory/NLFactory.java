package Factory;

import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;
import gui.UnRegisteredGUI;

public class NLFactory implements factory {
	BLFacade appFacadeInterface;

	public BLFacade getNL(Boolean b) {
		ConfigXML c = ConfigXML.getInstance();
		System.out.println(c.getLocale());
		Locale.setDefault(new Locale(c.getLocale()));
		System.out.println("Locale: " + Locale.getDefault());

		
		try {

			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			if (b) {
				DataAccess da = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
				appFacadeInterface = new BLFacadeImplementation(da);
			}

			else { // Si es remoto
				String serviceName = "http://" + c.getBusinessLogicNode() + ":" + c.getBusinessLogicPort() + "/ws/"
						+ c.getBusinessLogicName() + "?wsdl";
				URL url = new URL(serviceName);
				QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
				Service service = Service.create(url, qname);
				appFacadeInterface = service.getPort(BLFacade.class);
			}

		} catch (Exception e) {

			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}

		return appFacadeInterface;

	}
}
