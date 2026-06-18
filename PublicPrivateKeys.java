import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.IOException;

public class PublicPrivateKeys {
    public static void main(String[] args) throws IOException, CipherException {
        String ganacheUrl = "http://localhost:8545";
        Web3j web3 = Web3j.build(new HttpService(ganacheUrl));
        System.out.println("Connected to Ganache");

        String password = "Hamad";
        String walletDirectory = "./wallets/";
        String walletFileName = WalletUtils.generateNewWalletFile(password, walletDirectory, true);
        System.out.println("Wallet file created: " + walletFileName);

        Credentials credentials = WalletUtils.loadCredentials(password, walletDirectory + walletFileName);
        System.out.println("Public key (address): " + credentials.getAddress());
        System.out.println("Private key: " + Numeric.toHexString(credentials.getEcKeyPair().getPrivateKey()));
    }
}
