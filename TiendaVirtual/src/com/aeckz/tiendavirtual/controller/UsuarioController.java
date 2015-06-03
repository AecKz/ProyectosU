package com.aeckz.tiendavirtual.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.aeckz.tiendavirtual.dao.EntidadDAO;
import com.aeckz.tiendavirtual.dao.UsuarioDAO;
import com.aeckz.tiendavirtual.model.Entidad;
import com.aeckz.tiendavirtual.model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSONObject result = new JSONObject();
		try {
			String tipoConsulta = request.getParameter("tipoConsulta") == null ? ""
					: request.getParameter("tipoConsulta");
			String id = request.getParameter("codigoEnsurance") == null ? ""
					: request.getParameter("codigoEnsurance");
			String login = request.getParameter("login") == null ? ""
					: request.getParameter("login").toUpperCase();
			String clave = request.getParameter("clave") == null ? ""
					: request.getParameter("clave");
			String nombre = request.getParameter("nombre") == null ? ""
					: request.getParameter("nombre");
			String apellido = request.getParameter("apellido") == null ? ""
					: request.getParameter("apellido");
			String email = request.getParameter("email") == null ? ""
					: request.getParameter("email");
			
			
			JSONObject usuarioJSONObject = new JSONObject();
			JSONArray usuarioJSONArray = new JSONArray();

			Usuario usuario = new Usuario();
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			
			Entidad entidad = new Entidad();
			EntidadDAO entidadDAO = new EntidadDAO();

			if (!id.equals(""))
				usuario.setId(Integer.parseInt(id));
			
			if (!login.equals(""))
				usuario.setLogin(login);

			if (!clave.equals(""))
				usuario.setClave(clave);
			
			if (!nombre.equals(""))
				entidad.setNombre(nombre);
			
			if (!apellido.equals(""))
				entidad.setApellido(apellido);
			
			if (!email.equals(""))
				entidad.setEmail(email);
			
			if(tipoConsulta.equals("logeo")){
				String banderaLogin = usuarioDAO.login(login, clave);
				result.put("banderaLogin", banderaLogin);				
			}
			
			if(tipoConsulta.equals("registro")){
				entidadDAO.crear(entidad);
				if(entidad.getId()>0){
					usuario.setEntidad(entidad);
				}				
				usuarioDAO.crear(usuario);
				result.put("usuarioCreado", "1");
			}

			if (tipoConsulta.equals("encontrarTodos")) {
				List<Usuario> results = usuarioDAO.buscarTodos();
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					usuario = results.get(i);
					usuarioJSONObject.put("id", usuario.getId());
					usuarioJSONObject.put("login", usuario.getLogin());
					usuarioJSONObject.put("clave", usuario.getClave());					
					usuarioJSONArray.add(usuarioJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoUsuarios", usuarioJSONArray);
			}

			if (tipoConsulta.equals("crear"))
				usuarioDAO.crear(usuario);

			if (tipoConsulta.equals("actualizar"))
				usuarioDAO.editar(usuario);

			if (tipoConsulta.equals("eliminar"))
				usuarioDAO.eliminar(usuario);

			result.put("success", Boolean.TRUE);
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
		} catch (Exception e) {
			result.put("success", Boolean.FALSE);
			result.put("error", e.getLocalizedMessage());
			response.setContentType("application/json; charset=ISO-8859-1");
			result.write(response.getWriter());
			e.printStackTrace();

		}
	}

}
