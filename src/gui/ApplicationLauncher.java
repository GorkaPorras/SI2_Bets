package gui;

import Factory.NLFactory;
import Factory.factory;
import businessLogic.BLFacade;

public class ApplicationLauncher {

	public static void main(String[] args) {
		Boolean isLocal = true;
		UnRegisteredGUI HasieraPantaila = new UnRegisteredGUI();
		HasieraPantaila.setVisible(true);
		
		factory f = new NLFactory();
		BLFacade appFacadeInterface = f.getNL(isLocal);

		UnRegisteredGUI.setBussinessLogic(appFacadeInterface);
		RegisterGUI.setBussinessLogic(appFacadeInterface);
		FindQuestionsGUI.setBussinessLogic(appFacadeInterface);
		AdminGUI.setBussinessLogic(appFacadeInterface);
		LangileaGUI.setBussinessLogic(appFacadeInterface);
		ErabiltzaileaGUI.setBussinessLogic(appFacadeInterface);
		SeeMugimenduakGUI.setBussinessLogic(appFacadeInterface);
		InsertMoneyGUI.setBussinessLogic(appFacadeInterface);
		CreateFeesGUI.setBussinessLogic(appFacadeInterface);
		CreateQuestionGUI.setBussinessLogic(appFacadeInterface);
		CreateWorkerGUI.setBussinessLogic(appFacadeInterface);
		DeleteFeesGUI.setBussinessLogic(appFacadeInterface);
		DeleteQuestionsGUI.setBussinessLogic(appFacadeInterface);
		PutResultGUI.setBussinessLogic(appFacadeInterface);
		DeleteErabiltzaileaGUI.setBussinessLogic(appFacadeInterface);
		DeleteEventsGUI.setBussinessLogic(appFacadeInterface);
		GertaeraBatBikoiztuGUI.setBussinessLogic(appFacadeInterface);
		ApustuAnitzaGUI.setBussinessLogic(appFacadeInterface);

	}
}
