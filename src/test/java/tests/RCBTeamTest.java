package tests;


import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.Helper;

import java.util.List;

/**
 *
 * This test class consists tests regarding RCB Team List
 *
 * @author Swarnaprabha Ghosh
 */

public class RCBTeamTest {


    private Helper helper = new Helper();
    private JsonPath jsonPath;
    private String path;

    @BeforeClass
    public void setUp() {
        path = helper.readData("testdatapath");
        String  body = helper.readFileAsString(path);
        jsonPath =new JsonPath(body);
    }

    @Test(description ="Test for checking only 4 foreigner present in the team list ")
    public void fourForeignerTest(){

        Assert.assertEquals(jsonPath.get("name"),"Royal Challengers Bangalore");
        List<Object> teamList = jsonPath.getList("player");
        int teamSize = teamList.size();
        int count =0 ;
        if(teamSize==11){
            for(int i =0;i<teamSize;i++){
                String playerCountry =jsonPath.get("player["+i+"].country");
                if(!playerCountry.equalsIgnoreCase("India")){
                    count=count+1;
                }
            }
        }
        Assert.assertEquals(count,4);

    }

    @Test(description = "Test for checking at least one wicket keeper is there in the team or not")
    public void atLeastOneWicketKeeperTest(){
        Assert.assertEquals(jsonPath.get("name"),"Royal Challengers Bangalore");
        List<Object> teamList = jsonPath.getList("player");
        int teamSize = teamList.size();
        int wicketKeeper =0 ;
        for(int i =0;i<teamSize;i++){
            String playerrole =jsonPath.get("player["+i+"].role");
            if(playerrole.equalsIgnoreCase("Wicket-keeper")){
                wicketKeeper++;
            }
        }
        Assert.assertTrue(wicketKeeper>=1);

    }

}
