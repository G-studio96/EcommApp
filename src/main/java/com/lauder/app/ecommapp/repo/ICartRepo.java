package com.lauder.app.ecommapp.repo;



import com.lauder.app.ecommapp.model.CartModel;
import com.lauder.app.ecommapp.model.UsersModel;
import com.lauder.app.ecommapp.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.Optional;


@Repository
public interface ICartRepo extends JpaRepository<CartModel, Long> {
    Optional<CartModel> findByProductId(ProductModel productId);

    Optional<CartModel> findByQuantityGreaterThan(int Quantity);

    Optional<CartModel> findByTotalBetween(BigDecimal minTotal, BigDecimal maxTotal);

    Optional<CartModel> findByCartId(Long cartId);

    Optional<CartModel> findByCustomerId(UsersModel customer);

    int countByCustomerId(Long customerId);
}
