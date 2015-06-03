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
import com.aeckz.tiendavirtual.model.Entidad;

/**
 * Servlet implementation class EntidadController
 */
@WebServlet("/EntidadController")
public class EntidadController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EntidadController() {
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
			String nombre = request.getParameter("nombre") == null ? ""
					: request.getParameter("nombre").toUpperCase();
			String apellido = request.getParameter("apellido") == null ? ""
					: request.getParameter("apellido").toUpperCase();
			String identificacion = request.getParameter("identificacion") == null ? ""
					: request.getParameter("identificacion");
			String email = request.getParameter("email") == null ? "" : request
					.getParameter("email");
			String telefono = request.getParameter("telefono") == null ? ""
					: request.getParameter("telefono");
			// String direccionId = request.getParameter("direccionId") == null
			// ? "" : request.getParameter("direccionId");
			// String actividadEconomicaId =
			// request.getParameter("actividadEconomica") == null ? "" :
			// request.getParameter("actividadEconomica");
			// String descripcion = request.getParameter("descripcion") == null
			// ? "" : request.getParameter("descripcion");
			// String ciudadId = request.getParameter("ciudadId") == null ? "" :
			// request.getParameter("ciudadId");
			// String referencia = request.getParameter("referencia") == null ?
			// "" : request.getParameter("referencia");
			// String sectorId = request.getParameter("sectorId") == null ? "" :
			// request.getParameter("sectorId");

			JSONObject entidadJSONObject = new JSONObject();
			JSONArray entidadJSONArray = new JSONArray();

			Entidad entidad = new Entidad();
			EntidadDAO entidadDAO = new EntidadDAO();

			if (!id.equals(""))
				entidad.setId(Integer.parseInt(id));

			if (!identificacion.equals("")) {
				if (entidadDAO.buscarPorIdentificacion(identificacion) == 0)
					;
				entidad.setIdentificacion(identificacion);
			}

			if (!email.equals(""))
				entidad.setEmail(email);

			if (!telefono.equals(""))
				entidad.setTelefono(telefono);

			if (!nombre.equals(""))
				entidad.setNombre(nombre);

			if (!apellido.equals(""))
				entidad.setApellido(apellido);

			if (tipoConsulta.equals("encontrarTodos")) {
				List<Entidad> results = entidadDAO.buscarTodos();
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					entidad = results.get(i);
					entidadJSONObject.put("id", entidad.getId());
					entidadJSONObject.put("nombre", entidad.getNombre());
					entidadJSONObject.put("apellido", entidad.getApellido());
					entidadJSONObject.put("identificacion",
							entidad.getIdentificacion());
					entidadJSONObject.put("email", entidad.getEmail());
					entidadJSONObject.put("telefono", entidad.getTelefono());

					entidadJSONArray.add(entidadJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoEntidades", entidadJSONArray);
			}

			if (tipoConsulta.equals("crear"))
				entidadDAO.crear(entidad);

			if (tipoConsulta.equals("actualizar"))
				entidadDAO.editar(entidad);

			if (tipoConsulta.equals("eliminar"))
				entidadDAO.eliminar(entidad);

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
