package Ihm;

public class Main {

	public static void main(String[] args) {
		
		IhmLoader loader = new IhmLoader();
		
		Model model = new Model(loader);
		
		loader.setText("Chargement de l'interface...");
		
		new IhmLevelSelector(model);
		
		//JPanel game = new IhmGameSelector(model);
		
		// On affiche le jeu
		model.setMenuSelectorVisibility(true);
		loader.setVisible(false);
		
	}
}
