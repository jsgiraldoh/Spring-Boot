Spring-Boot
===

## Guía para un proyecto en spring

1) Crear un proyecto maven


2) Ir a spring.io

    https://spring.io/guides/gs/rest-service/

Guides **->** Maven

Copiar la siguiente linea

```htmlembedded=
<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.6.RELEASE</version>
</parent>
```

Y pegarlo en el **pom.xml**

Buscar la dependencia, started web y pegarla dentro del bloque de dependencias

```htmlembedded=
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
3) Crear un paquete en **src/main/java**

4) Crear una clase dentro de ese paquete, con un **método main**

5) Imprimir un mensaje

6) Crear un paquete controller

7) Crear una clase dentro del paquete controller

8) En la clase colocar la anotacion **@RestController**

7) Crear un método, con las siguientes características

```java=
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping(value="/hello-world", method= RequestMethod.GET)
	public String helloWorld() {
		return "Hello World desde controller";
	}

}
```

8) A la clase **Main** colocarle la siguiente etiqueta, **@SpringBootApplication**

9) Cambiar el método **main**

```
@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println("This app is works");

		SpringApplication.run(Main.class, args);
	}

}
```

10) Agregar en **src/main/resources** el archivo **application.properties**

```java=
#logging.level.org.springframework=debug
management.endpoints.enabled-by-default=false
management.endpoint.info.enabled=true
management.endpoint.beans.enabled=true
management.endpoint.health.enabled=true
```

11) Crear un paquete **service**, y crear una clase **service**, con la etiqueta **@service**

```java=
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

	public String getHelloWorld() {
		return "Hello world desde Service";
	}

}
```

12) Inyectando dependencia

```java=
@RestController
public class HelloWorldController {

	@Autowired
	private HelloWorldService helloWorldService;

	@RequestMapping(value="/hello-world", method= RequestMethod.GET)
	public String helloWorld() {
		return helloWorldService.getHelloWorld();
	}

}
```

13) Modificación de la inyección de dependencia para manejar **interface**

```java=
public interface IHelloWorldService {

	String getHelloWorld();

}

@Service
public class HelloWorldServiceImpl implements IHelloWorldService {

	public String getHelloWorld() {
		return "Hello world";
	}

}
```

14) Modificar el archivo **application.properties**

```java=
management.endpoints.enabled-by-default=false
management.endpoint.info.enabled=true
management.endpoint.beans.enabled=true
management.endpoint.health.enabled=true
spring.profiles.active=dev
```

15) Creamos el archivo **application-dev.properties**

```java=
management.endpoints.enabled-by-default=true
sprint.application.name=APP NAME DEV
```

16) Verificamos la segunda línea de la consola

17) Crear el archivo banner-dev.txt

    https://devops.datenkollektiv.de/banner.txt/index.html

18) Modificamos el archivo **banner-dev.txt**

```java=
spring.banner.location=classpath:banner-dev.txt
management.endpoints.enabled-by-default=true
sprint.application.name=APP NAME DEV
```

19) Crear un ***contenedor de docker con mysql***

```dockerfile=
docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_ALLOW_EMPTY_PASSWORD=false -e MYSQL_RANDOM_ROOT_PASSWORD=false mysql:latest
```

20) Agregar las dependencias para la base de datos

```htmlembedded=
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
	<scope>runtime</scope>
</dependency>
```

21) Modificar el archivo properties

```java=
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/db_example
spring.datasource.username=springuser
spring.datasource.password=ThePassword
```

22) Crear el paquete **entities**, dentro del paquete **entities**, crear la clase **usuario** de la siguiente manera

```java=
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Usuario {

	@Id
        @GeneratedValue(strategy= GenerationType.AUTO)
	private Integer id;

	private String name;

	private String email;

}
```

23) Crear el paquete **repositories** y dentro de este paquete, crear una interface, de la siguiente manera

```java=
@Repository
public interface IUserRepository extends CrudRepository<Usuario, Integer> {

}
```


24) Modificar el **controller**, para insertar en la ***base de datos***

```java=
@RestController
public class HelloWorldController {

	@Autowired
	private IHelloWorldService helloWorldServiceImpl;

	@Autowired
	private IUserRepository userRepository;

	@RequestMapping(value="/hello-world", method= RequestMethod.GET)
	public String helloWorld() {
		crearUsuario();
		return helloWorldServiceImpl.getHelloWorld();
	}

	private void crearUsuario(){
		Usuario usuario = new Usuario();
		usuario.setName("Usuario de pruebas");
		usuario.setEmail("usuario@pruebas.com");
		userRepository.save(usuario);
	}

}
```

25) Factorizar el código para enviar los parámetros

```java=
@RestController
public class HelloWorldController {

	@Autowired
	private IHelloWorldService helloWorldServiceImpl;

	@Autowired
	private IUserRepository userRepository;

	@RequestMapping(value="/hello-world", method= RequestMethod.GET)
	public String helloWorld(@RequestParam String username, @RequestParam String email) {
		crearUsuario(username, email);
		return helloWorldServiceImpl.getHelloWorld();
	}

	private void crearUsuario(String username, String email){
		Usuario usuario = new Usuario();
		usuario.setName(username);
		usuario.setEmail(email);
		userRepository.save(usuario);
	}

}
```

26) Crear la interface **IUserRepository**

```java=
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.test.principal.entities.Usuario;

@Repository
public interface IUserRepository extends CrudRepository<Usuario, Integer> {

}
```

27) Crear la interface **IUserService**

```java=
public interface IUserService {

	Usuario crearUsuario(Usuario usuario);

	Usuario actualizarUsuario(Usuario usuario);

	Usuario buscarPorId(Integer userId);

	List<Usuario> getUsuarios();

	void eliminarPorId(Integer userId);

}
```

28) Crear la implementación de esa interface, **UserServiceImpl**

```java=
@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public Usuario crearUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return userRepository.save(usuario);
	}

	@Override
	public Usuario actualizarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return userRepository.save(usuario);
	}

	@Override
	public Usuario buscarPorId(Integer userId) {
		// TODO Auto-generated method stub
		Optional<Usuario> optionalUsuario = userRepository.findById(userId);
		if(optionalUsuario.isPresent()){
			return optionalUsuario.get();
		}
	    throw new RuntimeException("Usuario no existe");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getUsuarios() {
		// TODO Auto-generated method stub
		List listUsers = new ArrayList<>();
		userRepository.findAll().forEach(p-> listUsers.add(p));
		return listUsers;
	}

	@Override
	public void eliminarPorId(Integer userId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(userId);

	}

`
```

29) Crear el controlador, **UserServiceController**

```java=
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.test.principal.entities.Usuario;
import com.test.principal.repositories.IUserService;

@RestController
@RequestMapping("/user")
public class UserServiceController {

	@Autowired
	private IUserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public Usuario saveUser(@RequestBody Usuario usuario) {
		return userService.crearUsuario(usuario);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Usuario updateUser(@RequestBody Usuario usuario) {
		return userService.actualizarUsuario(usuario);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Usuario> findAll() {
		return userService.getUsuarios();
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteUser(@RequestBody Usuario usuario) {
		userService.eliminarPorId(usuario.getId());
	}

	@RequestMapping(value = "/buscarPorId", method = RequestMethod.GET)
	public Usuario findById(@RequestParam Integer id){
		return userService.buscarPorId(id);
	}

}
```

30) Agregar el **plugin**, para crear la imagen de ***Docker***

```htmlembedded=
<properties>
	<java.version>1.8</java.version>
	<docker.image.prefix>docker.test</docker.image.prefix>
</properties>

<build>
	<plugins>

		<plugin>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-maven-plugin</artifactId>
		</plugin>

		<plugin>
                <groupId>com.spotify</groupId>
                <artifactId>docker-maven-plugin</artifactId>
                <version>0.4.13</version>
                <configuration>
                    <imageName>test/microservice</imageName>
                    <dockerDirectory>src/main/docker</dockerDirectory>
                    <resources>
                        <resource>
                            <targetPath>/</targetPath>
                            <directory>${project.build.directory}</directory>
                            <include>${project.build.finalName}.jar</include>
                        </resource>
                    </resources>
                </configuration>
            </plugin>

	</plugins>
</build>
```

31) Crear una carpeta **src/main/docker**

32) Crear el archivo ***Dockerfile***

```dockerfile=
FROM java:8-alpine
COPY testdocker.jar /home/
WORKDIR /home/
ENTRYPOINT java -jar springbootdocker.jar
EXPOSE 8080
```

33) Agregar en la étiqueta build

```htmlembedded=
<finalName>testdocker</finalName>
```

34) Run **->** Run configurations **->** Goals:

```
clean install docker:build
```

35) Cambiar el archivo **application.propierties**

```java=
spring.datasource.url=jdbc:mysql://${DOCKER_HOST}:3306/test
```

36) Run configurations **->** Environment **->** DOCKER_HOST **->** localhost

37) Copiar el nombre del contenedor

38)

```
docker inspect mysql
```

39) Buscar la **dirección ip** del contenedor de ***mysql***

40)

```dockerfile=
docker run -p 8080:8080 -e DOCKER_HOST=172.19.0.2 test/microservice
```

41)

```dockerfile=
docker run --network entorno_my_net -p 8080:8080 -e DOCKER_HOST=172.19.0.2 test/microservice
```

Links para su revisión.

https://es.wikipedia.org/wiki/Inversi%C3%B3n_de_control

https://stackoverflow.com/questions/130794/what-is-dependency-injection

Curso de Spring Boot en perfil Tic

Noviembre 17 del 2019
