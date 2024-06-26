package co.com.park.gp.data.dao.factory;

import co.com.park.gp.data.dao.entity.parqueaderos.CiudadDAO;
import co.com.park.gp.data.dao.entity.parqueaderos.DepartamentoDAO;
import co.com.park.gp.data.dao.entity.empleados.EmpleadoDAO;
import co.com.park.gp.data.dao.entity.parqueaderos.PaisDAO;
import co.com.park.gp.data.dao.entity.parqueaderos.ParqueaderoDAO;
import co.com.park.gp.data.dao.entity.parqueaderos.SedeDAO;
import co.com.park.gp.data.dao.entity.empleados.TipoEmpleadoDAO;
import co.com.park.gp.data.dao.entity.comunes.TipoIdentificacionDAO;
import co.com.park.gp.data.dao.entity.parqueaderos.TipoSedeDAO;
import co.com.park.gp.data.dao.factory.concrete.PostgresqlDAOFactory;

public interface DAOFactory {

	static DAOFactory getFactory() {
		return new PostgresqlDAOFactory();
	}

	void cerrarConexion();

	void iniciarTransaccion();

	void confirmarTransaccion();

	void cancelarTransaccion();

	PaisDAO getPaisDAO();

	DepartamentoDAO getDepartamentoDAO();

	CiudadDAO getCiudadDAO();

	ParqueaderoDAO getParqueaderoDAO();

	SedeDAO getSedeDAO();

	TipoSedeDAO getTipoSedeDAO();

	EmpleadoDAO getEmpleadoDAO();
	
	TipoEmpleadoDAO getTipoEmpleadoDAO();
	
	TipoIdentificacionDAO geTipoIdentificacionDAO();

}
