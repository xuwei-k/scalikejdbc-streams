package scalikejdbc.streams

import scalikejdbc.{ ConnectionPool, ConnectionPoolSettings, DBSession, NamedDB, SettingsProvider }

trait TestDBSettings {
  val settings: SettingsProvider = SettingsProvider.default.copy(loggingSQLAndTime = s => s.copy(singleLineMode = true))

  lazy val dbName = Symbol(this.getClass.getSimpleName)

  protected def openDB(): Unit = {
    val props = new Properties()
    props.load(getClass.getClassLoader.getResourceAsStream("jdbc.properties"))

    val url = props.getProperty("url").format(dbName.name)
    val user = props.getProperty("user")
    val password = props.getProperty("password")
    val driverClassName = props.getProperty("driverClassName")
    val poolSettings = ConnectionPoolSettings(driverName = driverClassName)
    Class.forName(poolSettings.driverName)
    ConnectionPool.add(dbName, url, user, password, poolSettings)
  }

  protected def closeDB(): Unit = {
    ConnectionPool.close(dbName)
  }

  protected def db: NamedDB = {
    NamedDB(dbName, settings)
  }

  protected def loadFixtures(f: DBSession => Unit): Unit = {
    val settings = SettingsProvider.default.copy(loggingSQLAndTime = s => s.copy(enabled = false))
    NamedDB(dbName, settings).localTx(f)
  }
}
