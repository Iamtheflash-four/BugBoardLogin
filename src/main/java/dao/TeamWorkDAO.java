package dao;

import java.util.ArrayList; 
import dto.TeamDTO;

public interface TeamWorkDAO {

	boolean creaTeamWork(long idResponsabile, String nome) throws Exception;

	ArrayList<TeamDTO> elencaTeamUtente(long idUtente) throws Exception;

	boolean creaProgetto(long idProgetto, long idTeam) throws Exception;

	boolean addUser(long idUtente, long idTeam) throws Exception;
}