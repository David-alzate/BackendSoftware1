package co.com.park.gp.controller;

import co.com.park.gp.business.facade.impl.empleado.EliminarEmpleadoFacade;
import co.com.park.gp.business.facade.impl.empleado.ModificarEmpleadoFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import co.com.park.gp.business.facade.impl.empleado.ConsultarEmpleadoFacade;
import co.com.park.gp.business.facade.impl.empleado.RegistrarEmpleadoFacade;
import co.com.park.gp.controller.response.EmpleadoResponse;
import co.com.park.gp.crosscutting.exceptions.GPException;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.MessageCatalogStrategy;
import co.com.park.gp.crosscutting.exceptions.messagecatalog.data.CodigoMensaje;
import co.com.park.gp.dto.EmpleadoDTO;

import java.util.UUID;

@RestController
@RequestMapping("/empleados/")
public final class EmpleadoController {

	@GetMapping
	public ResponseEntity<EmpleadoResponse> consultar() {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var empleadoResponse = new EmpleadoResponse();

		try {
			var empleadoDto = EmpleadoDTO.build();
			var facade = new ConsultarEmpleadoFacade();

			empleadoResponse.setDatos(facade.execute(empleadoDto));
			empleadoResponse.getMensajes().add(MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00090));

		} catch (final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			empleadoResponse.getMensajes().add(excepcion.getMensajeUsuario());

		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = MessageCatalogStrategy.getContenidoMensaje(CodigoMensaje.M00085);
			empleadoResponse.getMensajes().add(mensajeUsuario);
		}

		return new ResponseEntity<>(empleadoResponse, httpStatusCode);
	}

	@PostMapping
	public ResponseEntity<EmpleadoResponse> crear(@RequestBody EmpleadoDTO empleado) {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var empleadoResponse = new EmpleadoResponse();

		try {
			var facade = new RegistrarEmpleadoFacade();
			facade.execute(empleado);
			empleadoResponse.getMensajes().add("Empleado creado exitosamente");

		} catch (final GPException excepcion) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			empleadoResponse.getMensajes().add(excepcion.getMensajeUsuario());
		} catch (final Exception excepcion) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "se ha presentado un prblema tratando de registar el nuevo empleado";
			empleadoResponse.getMensajes().add(mensajeUsuario);
		}

		return new ResponseEntity<>(empleadoResponse, httpStatusCode);

	}

	@PutMapping("/{id}")
	public ResponseEntity<EmpleadoResponse> modificar(@PathVariable UUID id, @RequestBody EmpleadoDTO empleadoDTO) {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var empleadoResponse = new EmpleadoResponse();

		try {
			empleadoDTO.setId(id);
			var facade = new ModificarEmpleadoFacade();

			facade.execute(empleadoDTO);
			empleadoResponse.getMensajes().add("Empleado modificado existosamente ");
		} catch (final GPException exception) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			empleadoResponse.getMensajes().add(exception.getMensajeUsuario());
		} catch (final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "Se ha presentado un problema tratando de modificar la informacion del Empleado";
			empleadoResponse.getMensajes().add(mensajeUsuario);
		}
		return new ResponseEntity<>(empleadoResponse, httpStatusCode);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<EmpleadoResponse> eliminar(@PathVariable UUID id) {

		var httpStatusCode = HttpStatus.ACCEPTED;
		var empleadoResponse = new EmpleadoResponse();

		try {
			var facade = new EliminarEmpleadoFacade();
			facade.execute(id);
			empleadoResponse.getMensajes().add("Empleado eliminado existosamente ");
		} catch (final GPException exception) {
			httpStatusCode = HttpStatus.BAD_REQUEST;
			empleadoResponse.getMensajes().add(exception.getMensajeUsuario());
		} catch (final Exception exception) {
			httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;

			var mensajeUsuario = "Se ha presentado un problema tratando de eliminar la informacion del Empleado";
			empleadoResponse.getMensajes().add(mensajeUsuario);

		}
		return new ResponseEntity<>(empleadoResponse, httpStatusCode);
	}

}
