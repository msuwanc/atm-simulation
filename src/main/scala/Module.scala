import com.google.inject.AbstractModule
import daos.{AtmDao, AtmDaoImpl}
import services.{AtmService, AtmServiceImpl}
import utils.DatabaseConnection

class Module extends AbstractModule {
  override def configure(): Unit = {
    bind(classOf[DatabaseConnection]).toInstance(new DatabaseConnection)
    bind(classOf[AtmDao]).to(classOf[AtmDaoImpl])
    bind(classOf[AtmService]).to(classOf[AtmServiceImpl])
  }
}