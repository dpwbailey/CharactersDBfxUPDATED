package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.DBUtil;

public class CharacterEntityDAO {

  public static CharacterEntity searchCharacterEntity(String playerID)
      throws SQLException, ClassNotFoundException {
    CharacterEntity characterEntity;


      //Declare a SELECT statement
      String selectStmt = "SELECT * FROM CHARACTERS ORDER BY playerID";

      //Execute SELECT statement
      try {
        //Get ResultSet from dbExecuteQuery method
        ResultSet rsCharacterEntity = DBUtil.dbExecuteQuery(selectStmt);

        //Send ResultSet to the getAuthorFromResultSet method and get author object
        characterEntity = getCharacterEntityFromResultSet(rsCharacterEntity);

        //Return employee object
        return characterEntity;
      } catch (SQLException e) {
        System.out
            .println("While searching a player with " + playerID + " id, an error occurred: " + e);
        //Return exception
        throw e;
      }
    }
  //Use ResultSet from DB as parameter and set CharacterEntity Object's attributes and return author object.
  private static CharacterEntity getCharacterEntityFromResultSet(ResultSet rs) throws SQLException {
    CharacterEntity characterEntity = null;
    if (rs.next()) {
      characterEntity = new CharacterEntity();
      characterEntity.setPlayerID(rs.getInt("playerID"));
      characterEntity.setCharacterName(rs.getString("Character Name"));
      characterEntity.setCharClass(rs.getString("Class"));
    }
    return characterEntity;
  }
  //*******************************
  //SELECT Characters
  //*******************************
  public static ObservableList<CharacterEntity> searchCharacterEntities() throws SQLException, ClassNotFoundException {
    //Declare a SELECT statement
    String selectStmt = "SELECT * FROM Characters";

    //Execute SELECT statement
    try {
      //Get ResultSet from dbExecuteQuery method
      ResultSet rsAuth = DBUtil.dbExecuteQuery(selectStmt);

      //Send ResultSet to the getEmployeeList method and get employee object
      ObservableList<CharacterEntity> characterList = getCharacterEntityList(rsAuth);
//--------
      //Return employee object
      return characterList;
    } catch (SQLException e) {
      System.out.println("SQL select operation has been failed: " + e);
      //Return exception
      throw e;
    }
  }
  //Select * from authors operation
  public static ObservableList<CharacterEntity> getCharacterEntityList(ResultSet rs)
      throws SQLException, ClassNotFoundException {
    //Declare a observable List which comprises of Employee objects
    ObservableList<CharacterEntity> characterList = FXCollections.observableArrayList();

    while (rs.next()) {
      CharacterEntity characterEntity = new CharacterEntity();
      characterEntity.setPlayerID(rs.getInt("playerID"));
      characterEntity.setCharacterName(rs.getString("charName"));
      characterEntity.setCharClass(rs.getString("Class"));

      //Add employee to the ObservableList
      characterList.add(characterEntity);
    }
    //return empList (ObservableList of Employees)
    return characterList;
  }
  //*************************************
  //DELETE a player
  //*************************************
  public static void deleteCharacterEntityWithId(String playerId) throws SQLException, ClassNotFoundException {
    //Declare a DELETE statement
    String updateStmt =
        "DELETE FROM Characters " +
            "WHERE playerID =" + playerId;

    //Execute UPDATE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while DELETE Operation: " + e);
      throw e;
    }
  }
  //*************************************
  //INSERT a character
  //*************************************
  public static void insertCharacter(String playerID, String characterName, String className)
      throws SQLException, ClassNotFoundException {
    //Declare a DELETE statement
    String updateStmt =
        "INSERT INTO Characters " +
            "(playerID, characterName, className) " +
            "VALUES " +
            "('" + playerID + "', '" + characterName + "', '" + className + "')";

    //Execute DELETE operation
    try {
      DBUtil.dbExecuteUpdate(updateStmt);
    } catch (SQLException e) {
      System.out.print("Error occurred while DELETE Operation: " + e);
      throw e;
    }
  }
}


