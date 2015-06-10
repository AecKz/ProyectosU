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
import com.aeckz.tiendavirtual.dao.VentaDAO;
import com.aeckz.tiendavirtual.model.Pedido;
import com.aeckz.tiendavirtual.model.Venta;
import com.aeckz.tiendavirtual.util.Utilitarios;

/**
 * Servlet implementation class VentaController
 */
@WebServlet("/VentaController")
public class VentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VentaController() {
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
			String pedidoId = request.getParameter("pedidoId") == null ? ""
					: request.getParameter("pedidoId");
			String formaPago = request.getParameter("formaPago") == null ? ""
					: request.getParameter("formaPago").toUpperCase();
			String totalPedido = request.getParameter("totalPedido") == null ? ""
					: request.getParameter("totalPedido");
			String totalImpuestos = request.getParameter("totalImpuestos") == null ? "" : request
					.getParameter("totalImpuestos");
			String totalEnvio = request.getParameter("totalEnvio") == null ? ""
					: request.getParameter("totalEnvio");
			String totalVenta = request.getParameter("totalVenta") == null ? ""
					: request.getParameter("totalVenta");
			String estado = request.getParameter("estado") == null ? ""
					: request.getParameter("estado");
			
			JSONObject ventaJSONObject = new JSONObject();
			JSONArray ventaJSONArray = new JSONArray();

			Venta venta = new Venta();
			VentaDAO ventaDAO = new VentaDAO();

			if (!id.equals(""))
				venta.setId(Integer.parseInt(id));

			if (!pedidoId.equals("")){
				Pedido pedido = new Pedido();
				PedidoDAO pedidoDAO = new PedidoDAO();
				pedido = pedidoDAO.buscarPorId(pedidoId);
				venta.setPedido(pedido);
			}
			
			if(!formaPago.equals(""))
				venta.setFormapago(formaPago);
			
			if(!totalPedido.equals(""))
				venta.setTotalpedido(Double.parseDouble(totalPedido));
			
			if(!totalImpuestos.equals(""))
				venta.setTotalimpuestos(Double.parseDouble(totalImpuestos));
			
			if(!totalEnvio.equals(""))
				venta.setTotalenvio(Double.parseDouble(totalEnvio));
			
			if(!totalVenta.equals(""))
				venta.setTotalventa(Double.parseDouble(totalVenta));
			
			if(!estado.equals(""))
				venta.setEstado(estado);
			
			//Falta fechActualiza			

			if (tipoConsulta.equals("crear")){
				venta.setFechaactualiza(Utilitarios.getFechaActual());
				ventaDAO.crear(venta);
			}
			if (tipoConsulta.equals("actualizar"))
				ventaDAO.editar(venta);

			if (tipoConsulta.equals("eliminar"))
				ventaDAO.eliminar(venta);

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
