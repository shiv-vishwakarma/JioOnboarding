package telcoHandsOn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class PlayersService {

	List<Players> list = new ArrayList<Players>();

	public void createList() {
		list.add(new Players("Rashford", "Manchester United", "England"));
		list.add(new Players("Bruno", "Manchester United", "England"));
		list.add(new Players("Ronaldo", "Juventus", "Itay"));
		list.add(new Players("Ramos", "Real Madrid", "Spain"));
	}

	public List<Players> getPlayersList() {
		//return list;
		return null;
	}

	public String deletePlayer(String name) {
		String status = null;
		ListIterator<Players> itr = list.listIterator();

		while (itr.hasNext()) {
			Players p = itr.next();
			if (p.getName().equalsIgnoreCase(name)) {
				itr.remove();
				status = "Player removed successfully !!";
			}
		}

		return status;
	}

	/*
	 * public Players getPlayer(String name) {
	 * 
	 * Players player = null; for (Players p : list) { if
	 * ((p.getName()).equalsIgnoreCase(name)) { player = p; } } return player; }
	 */

	public String addPlayer(Players player) {
		String status = null;
		if (player != null) {
			list.add(player);
			status = "Player added successfully !!";
		} else {
			status = "Player addition failed !!";
		}
		return status;
	}

}
