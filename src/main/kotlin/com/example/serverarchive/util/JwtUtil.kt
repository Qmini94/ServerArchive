import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import java.util.*

@Component
class JwtUtil {
	private val SECRET_KEY: SecretKey =
		SecretKeySpec("D4F7A2E3B4C9D5A8E9F7C6B2A3F5E8D9\n".toByteArray(), SignatureAlgorithm.HS256.jcaName)

	fun extractUsername(token: String): String {
		return extractClaim(token, Claims::getSubject)
	}

	fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
		val claims = extractAllClaims(token)
		return claimsResolver(claims)
	}

	private fun extractAllClaims(token: String): Claims {
		return Jwts.parserBuilder()
			.setSigningKey(SECRET_KEY)
			.build()
			.parseClaimsJws(token)
			.body
	}

	fun generateToken(username: String): String {
		val claims = HashMap<String, Any>()
		return createToken(claims, username)
	}

	private fun createToken(claims: Map<String, Any>, subject: String): String {
		return Jwts.builder()
			.setClaims(claims)
			.setSubject(subject)
			.setIssuedAt(Date(System.currentTimeMillis()))
			.setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours validity
			.signWith(SECRET_KEY, SignatureAlgorithm.HS256)
			.compact()
	}

	fun validateToken(token: String, username: String): Boolean {
		val extractedUsername = extractUsername(token)
		return extractedUsername == username && !isTokenExpired(token)
	}

	private fun isTokenExpired(token: String): Boolean {
		return extractExpiration(token).before(Date())
	}

	private fun extractExpiration(token: String): Date {
		return extractClaim(token, Claims::getExpiration)
	}
}
