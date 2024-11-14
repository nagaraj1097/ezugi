package api_genericutility;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class JavaUtility {

	public long getRandomNum() {
		Random random = new Random();
		long randomNumber = 1000000000L + (long) (random.nextDouble() * 9000000000L);
		return randomNumber;
	}

	public String getUuid() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	public long getCurrentTimeStamp() {
		long currentTimestamp = System.currentTimeMillis();
		return currentTimestamp;
	}

	public String getgenerateHMACSHA256(String data, String secretKey)
			throws InvalidKeyException, NoSuchAlgorithmException {
		Mac sha256Hmac = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
		sha256Hmac.init(secretKeySpec);

		// Generate the HMAC
		byte[] hmacBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));

		// Encode in Base64
		return Base64.getEncoder().encodeToString(hmacBytes);

	}

	public long getRandomNum(int numDigits) {

		if (numDigits < 1) {
			throw new IllegalArgumentException("Number of digits must be at least 1");
		}

		Random random = new Random();
		int lowerBound = (int) Math.pow(10, numDigits - 1); // Minimum value with the required digits
		int upperBound = (int) Math.pow(10, numDigits); // Maximum value + 1
		return random.nextInt(upperBound - lowerBound) + lowerBound;
	}

	public String getCurrentTimeInMilliseconds() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS");
		String currentTime = LocalDateTime.now().format(formatter);
		return currentTime;

	}

}
