# Web3 Java

Java examples for interacting with the Ethereum blockchain using the Web3j library, designed for Ganache local development.

## Files

- **PublicPrivateKeys.java** — Generate a wallet file, load credentials, and display public/private keys
- **BlockChainBasedSecurity.java** — Interact with smart contracts: register controllers and send transactions on the blockchain

## Requirements

- Java 8+
- Web3j library

## Setup

Add Web3j dependency to your project:

**Maven:**
```xml
<dependency>
    <groupId>org.web3j</groupId>
    <artifactId>core</artifactId>
    <version>4.9.0</version>
</dependency>
```

**Gradle:**
```gradle
implementation 'org.web3j:core:4.9.0'
```

## Usage

1. Start Ganache on `localhost:8545`
2. Compile: `javac -cp web3j-core.jar:. PublicPrivateKeys.java`
3. Run: `java -cp web3j-core.jar:. PublicPrivateKeys`
