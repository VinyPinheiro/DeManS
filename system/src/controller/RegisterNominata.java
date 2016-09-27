package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDao;
import dao.NominataDao;
import exception.AddressException;
import exception.MemberException;
import exception.NominataException;
import exception.OfficeException;
import exception.UfException;
import model.Member;
import model.Nominata;
import model.Office;

public class RegisterNominata extends HttpServlet {

	/**
	 * Method to receive POST data and verify and send result to client
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {
			Nominata nominata = new Nominata();

			for (String offices : Office.VALID_OFFICES_WITHOUT_ESPECIAL_CHARACTERS) {

				String requestResult = request.getParameter(offices);

				Office office;
				if (!requestResult.isEmpty()) {
					Member member = MemberDao.findById(Integer.parseInt(requestResult));
					office = new Office(member, offices);
				} else {
					office = new Office(offices);
				}

				nominata.addOffice(office);
			}

			NominataDao nominataDao = new NominataDao(nominata);
			nominataDao.register();
			
			final String error = "<script>alert('Salvo com sucesso.');location.href='home.jsp'</script>";
			out.print(error);

		} catch (SQLException e) {
			if (e.getMessage().contains("belongs1_UK")) {
				final String error = "<script>alert('Existe(m) membro(s) com mais que um cargo.'); history.go(-1);</script>";
				out.print(error);
			} else{
				if (e.getMessage().contains("belongs_UK")) {
					final String error = "<script>alert('Existe(m) cargo(s) com mais que um membro.'); history.go(-1);</script>";
					out.print(error);
				} else {
					if (e.getMessage().contains("nominata_UK")) {
						final String error = "<script>alert('O semestre que se segue já tem nominata cadastrada."+
								"Vá em editar nominata e selecione o semestre.'); history.go(-1);</script>";
						out.print(error);
					} else {
						final String error = "<script>alert('Ouve um erro, tente novamente'); history.go(-1);</script>";
						out.print(error);
					}
				}
			}
		} catch (NominataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UfException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemberException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OfficeException e) {
			final String error = "<script>alert('" + e.getMessage() + "'); history.go(-1);</script>";
			out.print(error);
		}
	}

}
