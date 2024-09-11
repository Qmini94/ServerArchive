import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.*
import kotlin.jvm.Throws

@RestController
class AuthenticationController(
	private val authenticationManager: AuthenticationManager,
	private val jwtUtil: JwtUtil,
	private val userDetailsService: UserDetailsService
) {

	@PostMapping("/authenticate")
	@Throws(Exception::class)
	fun createAuthenticationToken(@RequestBody authenticationRequest: AuthenticationRequest): ResponseEntity<*> {
		try {
			authenticationManager.authenticate(
				UsernamePasswordAuthenticationToken(authenticationRequest.username, authenticationRequest.password)
			)
		} catch (e: Exception) {
			throw Exception("INVALID_CREDENTIALS", e)
		}

		val userDetails: UserDetails = userDetailsService.loadUserByUsername(authenticationRequest.username)
		val jwt = jwtUtil.generateToken(userDetails.username)

		return ResponseEntity.ok(AuthenticationResponse(jwt))
	}
}

data class AuthenticationRequest(val username: String, val password: String)
data class AuthenticationResponse(val jwt: String)
