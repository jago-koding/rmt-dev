/**
 * @author peripurnama
 * @since Jul 24, 2020
 * @email cisvapery@gmail.com
 * 
 */
package id.co.bankmandiri.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import id.co.bankmandiri.user.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{

	Optional<UserEntity> findByEmail(String email);

	UserEntity findByUserId(String userId);
	
}
