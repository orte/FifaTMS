package LP;

/**
 * Clase que solo sirve para crear las ventanas que cumplen las diferentes funciones del programa
 * @author jon.orte
 *
 */
public class clsMenu {

	public void AltaClub(){
		NuevoClubFrm a=new NuevoClubFrm();
		a.setVisible(true);
	}
	public void ModificarClub(){
		ModificarClubFrm a=new ModificarClubFrm();
		a.setVisible(true);
	}
	public void NuevoFichaje(){
		SeleccionarClubFrm a=new SeleccionarClubFrm();
		a.setVisible(true);
	}
	public void CancelarTraspaso(){
		CancelarFichajeFrm a=new CancelarFichajeFrm();
		a.setVisible(true);
	}
	public void  ListaFichajes(){
		ListaClubesFrm a=new ListaClubesFrm();
		a.setVisible(true);
	}
	public void RankingFichajes(){
		RankingAgentessFrm a=new RankingAgentessFrm();
		a.setVisible(true);
	}
	public void ListaJugadores(){
		TablasJugadoresFrm a=new TablasJugadoresFrm();
		a.setVisible(true);
	}
	public void RankingAgentes(){
		RankingTraspasosFrm a=new RankingTraspasosFrm();
		a.setVisible(true);
	}
	public void RankingPaises(){
		ListaPaisesFrm a=new ListaPaisesFrm();
		a.setVisible(true);
	}
}
