package telcoHandsOn;

public class Players {

	private String name;
	private String clubName;
	private String country;

	public Players() {
	}

	public Players(String name, String clubName, String country) {
		super();
		this.name = name;
		this.clubName = clubName;
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Players [name=" + name + ", clubName=" + clubName + ", country=" + country + "]";
	}

}
