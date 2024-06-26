package co.com.park.gp.business.usecase.impl.parqueaderos.parqueadero;

import co.com.park.gp.business.domain.parqueaderos.ParqueaderoDomain;
import co.com.park.gp.business.usecase.UseCaseWithoutReturn;
import co.com.park.gp.crosscutting.exceptions.custom.BusinessGPException;
import co.com.park.gp.crosscutting.helpers.ObjectHelper;
import co.com.park.gp.data.dao.factory.DAOFactory;
import co.com.park.gp.entity.parqueaderos.ParqueaderoEntity;

public class ModificarParqueadero implements UseCaseWithoutReturn<ParqueaderoDomain> {

    private final DAOFactory factory;

    public ModificarParqueadero(final DAOFactory factory) {
        if (ObjectHelper.getObjectHelper().isNull(factory)) {
            var mensajeUsuario = "Se ha presentado un problema tratando de llevar una modificacion del parqueadero...";
            var mensajeTecnico = "El DAO factory para modificar el parqueadero llegó nulo...";
            throw new BusinessGPException(mensajeUsuario, mensajeTecnico);
        }
        this.factory = factory;
    }

    @Override
    public void execute(ParqueaderoDomain data) {
        var parqueaderoEntity = ParqueaderoEntity.build()
                .setId(data.getId())
                .setNombre(data.getNombre());

        factory.getParqueaderoDAO().modificar(parqueaderoEntity);

    }
}
