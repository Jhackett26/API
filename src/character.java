import java.util.ArrayList;

public class character {

public String name;
public String affiliation;
public ArrayList enemies = new ArrayList<String>();
public ArrayList allies = new ArrayList<String>();

public character(String nameParam, String affiliationParam, ArrayList enemiesParam, ArrayList alliesParam){
    name = nameParam;
    affiliation = affiliationParam;
    enemies = enemiesParam;
    allies = alliesParam;
}


}
