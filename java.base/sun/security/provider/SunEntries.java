/*
 * Copyright (c) 1996, 2020, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package sun.security.provider;

import java.io.*;
import java.net.*;
import java.util.*;
import java.security.*;

import jdk.internal.util.StaticProperty;
import sun.security.action.GetPropertyAction;
import sun.security.util.SecurityProviderConstants;
import static sun.security.util.SecurityProviderConstants.getAliases;

/**
 * Defines the entries of the SUN provider.
 *
 * Algorithms supported, and their names:
 *
 * - SHA is the message digest scheme described in FIPS 180-1.
 *   Aliases for SHA are SHA-1 and SHA1.
 *
 * - SHA1withDSA is the signature scheme described in FIPS 186.
 *   (SHA used in DSA is SHA-1: FIPS 186 with Change No 1.)
 *   Aliases for SHA1withDSA are DSA, DSS, SHA/DSA, SHA-1/DSA, SHA1/DSA,
 *   SHAwithDSA, DSAWithSHA1, and the object
 *   identifier strings "OID.1.3.14.3.2.13", "OID.1.3.14.3.2.27" and
 *   "OID.1.2.840.10040.4.3".
 *
 * - SHA-2 is a set of message digest schemes described in FIPS 180-2.
 *   SHA-2 family of hash functions includes SHA-224, SHA-256, SHA-384,
 *   and SHA-512.
 *
 * - SHA-224withDSA/SHA-256withDSA are the signature schemes
 *   described in FIPS 186-3. The associated object identifiers are
 *   "OID.2.16.840.1.101.3.4.3.1", and "OID.2.16.840.1.101.3.4.3.2".
 *
 * - DSA is the key generation scheme as described in FIPS 186.
 *   Aliases for DSA include the OID strings "OID.1.3.14.3.2.12"
 *   and "OID.1.2.840.10040.4.1".
 *
 * - MD5 is the message digest scheme described in RFC 1321.
 *   There are no aliases for MD5.
 *
 * - X.509 is the certificate factory type for X.509 certificates
 *   and CRLs. Aliases for X.509 are X509.
 *
 * - PKIX is the certification path validation algorithm described
 *   in RFC 5280. The ValidationAlgorithm attribute notes the
 *   specification that this provider implements.
 *
 * - JavaPolicy is the default file-based Policy type.
 *
 * - JavaLoginConfig is the default file-based LoginModule Configuration type.
 */

public final class SunEntries {

    // the default algo used by SecureRandom class for new SecureRandom() calls
    public static final String DEF_SECURE_RANDOM_ALGO;

    SunEntries(Provider p) {
        services = new LinkedHashSet<>(50, 0.9f);

        // start populating content using the specified provider

        // common attribute map
        HashMap<String, String> attrs = new HashMap<>(3);

        /*
         * SecureRandom engines
         */
        attrs.put("ThreadSafe", "true");
        if (NativePRNG.isAvailable()) {
            add(p, "SecureRandom", "NativePRNG",
                    "sun.security.provider.NativePRNG", attrs);
        }
        if (NativePRNG.Blocking.isAvailable()) {
            add(p, "SecureRandom", "NativePRNGBlocking",
                    "sun.security.provider.NativePRNG$Blocking", attrs);
        }
        if (NativePRNG.NonBlocking.isAvailable()) {
            add(p, "SecureRandom", "NativePRNGNonBlocking",
                    "sun.security.provider.NativePRNG$NonBlocking", attrs);
        }
        attrs.put("ImplementedIn", "Software");
        add(p, "SecureRandom", "DRBG", "sun.security.provider.DRBG", attrs);
        add(p, "SecureRandom", "SHA1PRNG",
                "sun.security.provider.SecureRandom", attrs);

        /*
         * Signature engines
         */
        attrs.clear();
        String dsaKeyClasses = "java.security.interfaces.DSAPublicKey" +
                "|java.security.interfaces.DSAPrivateKey";
        attrs.put("SupportedKeyClasses", dsaKeyClasses);
        attrs.put("ImplementedIn", "Software");

        attrs.put("KeySize", "1024"); // for NONE and SHA1 DSA signatures

        addWithAlias(p, "Signature", "SHA1withDSA",
                "sun.security.provider.DSA$SHA1withDSA", attrs);
        addWithAlias(p, "Signature", "NONEwithDSA",
                "sun.security.provider.DSA$RawDSA", attrs);

        attrs.put("KeySize", "2048"); // for SHA224 and SHA256 DSA signatures

        addWithAlias(p, "Signature", "SHA224withDSA",
                "sun.security.provider.DSA$SHA224withDSA", attrs);
        addWithAlias(p, "Signature", "SHA256withDSA",
                "sun.security.provider.DSA$SHA256withDSA", attrs);

        attrs.remove("KeySize");

        add(p, "Signature", "SHA1withDSAinP1363Format",
                "sun.security.provider.DSA$SHA1withDSAinP1363Format");
        add(p, "Signature", "NONEwithDSAinP1363Format",
                "sun.security.provider.DSA$RawDSAinP1363Format");
        add(p, "Signature", "SHA224withDSAinP1363Format",
                "sun.security.provider.DSA$SHA224withDSAinP1363Format");
        add(p, "Signature", "SHA256withDSAinP1363Format",
                "sun.security.provider.DSA$SHA256withDSAinP1363Format");

        /*
         *  Key Pair Generator engines
         */
        attrs.clear();
        attrs.put("ImplementedIn", "Software");
        attrs.put("KeySize", "2048"); // for DSA KPG and APG only

        String dsaKPGImplClass = "sun.security.provider.DSAKeyPairGenerator$";
        dsaKPGImplClass += (useLegacyDSA? "Legacy" : "Current");
        addWithAlias(p, "KeyPairGenerator", "DSA", dsaKPGImplClass, attrs);

        /*
         * Algorithm Parameter Generator engines
         */
        addWithAlias(p, "AlgorithmParameterGenerator", "DSA",
                "sun.security.provider.DSAParameterGenerator", attrs);
        attrs.remove("KeySize");

        /*
         * Algorithm Parameter engines
         */
        addWithAlias(p, "AlgorithmParameters", "DSA",
                "sun.security.provider.DSAParameters", attrs);

        /*
         * Key factories
         */
        addWithAlias(p, "KeyFactory", "DSA",
                "sun.security.provider.DSAKeyFactory", attrs);

        /*
         * Digest engines
         */
        add(p, "MessageDigest", "MD2", "sun.security.provider.MD2", attrs);
        add(p, "MessageDigest", "MD5", "sun.security.provider.MD5", attrs);
        addWithAlias(p, "MessageDigest", "SHA-1", "sun.security.provider.SHA",
                attrs);

        addWithAlias(p, "MessageDigest", "SHA-224",
                "sun.security.provider.SHA2$SHA224", attrs);
        addWithAlias(p, "MessageDigest", "SHA-256",
                "sun.security.provider.SHA2$SHA256", attrs);
        addWithAlias(p, "MessageDigest", "SHA-384",
                "sun.security.provider.SHA5$SHA384", attrs);
        addWithAlias(p, "MessageDigest", "SHA-512",
                "sun.security.provider.SHA5$SHA512", attrs);
        addWithAlias(p, "MessageDigest", "SHA-512/224",
                "sun.security.provider.SHA5$SHA512_224", attrs);
        addWithAlias(p, "MessageDigest", "SHA-512/256",
                "sun.security.provider.SHA5$SHA512_256", attrs);
        addWithAlias(p, "MessageDigest", "SHA3-224",
                "sun.security.provider.SHA3$SHA224", attrs);
        addWithAlias(p, "MessageDigest", "SHA3-256",
                "sun.security.provider.SHA3$SHA256", attrs);
        addWithAlias(p, "MessageDigest", "SHA3-384",
                "sun.security.provider.SHA3$SHA384", attrs);
        addWithAlias(p, "MessageDigest", "SHA3-512",
                "sun.security.provider.SHA3$SHA512", attrs);

        /*
         * Certificates
         */
        addWithAlias(p, "CertificateFactory", "X.509",
                "sun.security.provider.X509Factory", attrs);

        /*
         * KeyStore
         */
        add(p, "KeyStore", "PKCS12",
                "sun.security.pkcs12.PKCS12KeyStore$DualFormatPKCS12");
        add(p, "KeyStore", "JKS",
                "sun.security.provider.JavaKeyStore$DualFormatJKS", attrs);
        add(p, "KeyStore", "CaseExactJKS",
                "sun.security.provider.JavaKeyStore$CaseExactJKS", attrs);
        add(p, "KeyStore", "DKS", "sun.security.provider.DomainKeyStore$DKS",
                attrs);


        /*
         * CertStores
         */
        add(p, "CertStore", "Collection",
                "sun.security.provider.certpath.CollectionCertStore",
                attrs);
        add(p, "CertStore", "com.sun.security.IndexedCollection",
                "sun.security.provider.certpath.IndexedCollectionCertStore",
                attrs);

        /*
         * Policy
         */
        add(p, "Policy", "JavaPolicy", "sun.security.provider.PolicySpiFile");

        /*
         * Configuration
         */
        add(p, "Configuration", "JavaLoginConfig",
                "sun.security.provider.ConfigFile$Spi");

        /*
         * CertPathBuilder and CertPathValidator
         */
        attrs.clear();
        attrs.put("ValidationAlgorithm", "RFC5280");
        attrs.put("ImplementedIn", "Software");

        add(p, "CertPathBuilder", "PKIX",
                "sun.security.provider.certpath.SunCertPathBuilder",
                attrs);
        add(p, "CertPathValidator", "PKIX",
                "sun.security.provider.certpath.PKIXCertPathValidator",
                attrs);
    }

    Iterator<Provider.Service> iterator() {
        return services.iterator();
    }

    private void add(Provider p, String type, String algo, String cn) {
        services.add(new Provider.Service(p, type, algo, cn, null, null));
    }

    private void add(Provider p, String type, String algo, String cn,
            HashMap<String, String> attrs) {
        services.add(new Provider.Service(p, type, algo, cn, null, attrs));
    }

    private void addWithAlias(Provider p, String type, String algo, String cn,
            HashMap<String, String> attrs) {
        services.add(new Provider.Service(p, type, algo, cn,
            getAliases(algo), attrs));
    }

    private LinkedHashSet<Provider.Service> services;

    // name of the *System* property, takes precedence over PROP_RNDSOURCE
    private static final String PROP_EGD = "java.security.egd";
    // name of the *Security* property
    private static final String PROP_RNDSOURCE = "securerandom.source";

    private static final boolean useLegacyDSA =
        Boolean.parseBoolean(GetPropertyAction.privilegedGetProperty
            ("jdk.security.legacyDSAKeyPairGenerator"));

    static final String URL_DEV_RANDOM = "file:/dev/random";
    static final String URL_DEV_URANDOM = "file:/dev/urandom";

    private static final String seedSource;

    static {
        seedSource = AccessController.doPrivileged(
                new PrivilegedAction<String>() {

            @Override
            public String run() {
                String egdSource = System.getProperty(PROP_EGD, "");
                if (egdSource.length() != 0) {
                    return egdSource;
                }
                egdSource = Security.getProperty(PROP_RNDSOURCE);
                if (egdSource == null) {
                    return "";
                }
                return egdSource;
            }
        });

        DEF_SECURE_RANDOM_ALGO  = (NativePRNG.isAvailable() &&
            (seedSource.equals(URL_DEV_URANDOM) ||
             seedSource.equals(URL_DEV_RANDOM)) ?
            "NativePRNG" : "DRBG");
    }

    static String getSeedSource() {
        return seedSource;
    }

    /*
     * Use a URI to access this File. Previous code used a URL
     * which is less strict on syntax. If we encounter a
     * URISyntaxException we make best efforts for backwards
     * compatibility. e.g. space character in deviceName string.
     *
     * Method called within PrivilegedExceptionAction block.
     *
     * Moved from SeedGenerator to avoid initialization problems with
     * signed providers.
     */
    static File getDeviceFile(URL device) throws IOException {
        try {
            URI deviceURI = device.toURI();
            if(deviceURI.isOpaque()) {
                // File constructor does not accept opaque URI
                URI localDir = new File(
                    StaticProperty.userDir()).toURI();
                String uriPath = localDir.toString() +
                                     deviceURI.toString().substring(5);
                return new File(URI.create(uriPath));
            } else {
                return new File(deviceURI);
            }
        } catch (URISyntaxException use) {
            /*
             * Make best effort to access this File.
             * We can try using the URL path.
             */
            return new File(device.getPath());
        }
    }
}