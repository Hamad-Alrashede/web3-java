import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;


import java.io.IOException;


public class PublicPrivateKeys {
    public static void main(String[] args) throws IOException, CipherException {
        // Connect to Ganache
        String ganacheUrl = "http://localhost:8545";
        Web3j web3 = Web3j.build(new HttpService(ganacheUrl));


        // Create an account
        Credentials credentials = WalletUtils.loadBip39Credentials(null, WalletUtils.generateNewWalletFile("Hamad", new java.io.File("."), false));


        // Print the public and private key for the account respectively
        System.out.println("Public Key (Address): " + credentials.getAddress());
        System.out.println("Private Key: " + credentials.getEcKeyPair().getPrivateKey().toString(16));


        // To convert private key to hex format
        System.out.println("The private key is " + Numeric.toHexStringWithPrefix(credentials.getEcKeyPair().getPrivateKey()));


        // To encrypt the private key with a password "Hamad"
        String walletFileName = WalletUtils.generateWalletFile("Hamad", credentials.getEcKeyPair(), new java.io.File("."), false);
        System.out.println("\nCipher key (Wallet File): " + walletFileName);


        // To decrypt the cipher key
        Credentials decryptedCredentials = WalletUtils.loadCredentials("Hamad", walletFileName);


        // To convert the plain key to hex
        System.out.println("\nPlainKey = " + Numeric.toHexStringWithPrefix(decryptedCredentials.getEcKeyPair().getPrivateKey()));
    }
}
