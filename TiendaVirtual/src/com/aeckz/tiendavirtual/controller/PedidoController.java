package com.aeckz.tiendavirtual.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.aeckz.tiendavirtual.dao.PedidoDAO;
import com.aeckz.tiendavirtual.dao.ProductoDAO;
import com.aeckz.tiendavirtual.dao.UsuarioDAO;
import com.aeckz.tiendavirtual.model.Pedido;
import com.aeckz.tiendavirtual.model.Producto;
import com.aeckz.tiendavirtual.model.Usuario;
import com.aeckz.tiendavirtual.util.Utilitarios;

/**
 * Servlet implementation class PedidoController
 */
@WebServlet("/PedidoController")
public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PedidoController() {
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
			String id = request.getParameter("id") == null ? ""
					: request.getParameter("id");
			String productoId = request.getParameter("productoId") == null ? ""
					: request.getParameter("productoId");
			String usuarioId = request.getParameter("usuarioId") == null ? ""
					: request.getParameter("usuarioId");
			String estado = request.getParameter("estado") == null ? ""
					: request.getParameter("estado");
						
			JSONObject pedidoJSONObject = new JSONObject();
			JSONArray pedidoJSONArray = new JSONArray();

			Pedido pedido = new Pedido();
			PedidoDAO pedidoDAO = new PedidoDAO();

			if (!id.equals(""))
				pedido.setId(Integer.parseInt(id));

			if (!productoId.equals("")){
				Producto producto = new Producto();
				ProductoDAO productoDAO = new ProductoDAO();
				producto = productoDAO.buscarPorId(productoId);
				pedido.setProducto(producto);
			}			
						
			if(!estado.equals(""))
				pedido.setEstado(estado);
			
			if (!usuarioId.equals("")){
				Usuario usuario = new Usuario();
				UsuarioDAO usuarioDAO = new UsuarioDAO();
				usuario = usuarioDAO.buscarPorId(usuarioId);
				pedido.setUsuario(usuario);
			}
			
			//Agregar cosas al carrito
			if (tipoConsulta.equals("crear")){
				pedido.setFechaingreso(Utilitarios.getFechaActual());
				pedido.setFechaactualiza(Utilitarios.getFechaActual());
				pedidoDAO.crear(pedido);
			}
			
			if (tipoConsulta.equals("encontrarActivosUsuario")) {
				List<Pedido> results = pedidoDAO.buscarActivosUsuario(usuarioId);
				int i = 0;
				for (i = 0; i < results.size(); i++) {
					pedido = results.get(i);
					pedidoJSONObject.put("id", pedido.getId());
					pedidoJSONObject.put("producto", pedido.getProducto().getNombre());
					pedidoJSONObject.put("precioPublico", pedido.getProducto().getPreciopublico());
					pedidoJSONObject.put("fecha", pedido.getFechaactualiza().toString());
					pedidoJSONArray.add(pedidoJSONObject);
				}
				result.put("numRegistros", i);
				result.put("listadoPedidos", pedidoJSONArray);
			}
			
			if (tipoConsulta.equals("actualizar")){
				pedidoDAO.editar(pedido);
			}
			if (tipoConsulta.equals("eliminar")){
				pedidoDAO.eliminar(pedido);
			}
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
