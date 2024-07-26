package cyber.login.jwt.system.loginsystemjwt.produtos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import cyber.login.jwt.system.loginsystemjwt.produtos.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
