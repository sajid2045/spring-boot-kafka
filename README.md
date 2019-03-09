
curl -X POST -F "message=t1" http://localhost:9000/kafka/subscription/new
curl -X POST -F "message=t2" http://localhost:9000/kafka/subscription/resubscribe

Important Docs:

```    
https://medium.com/@danieljameskay/understanding-the-enable-auto-commit-kafka-consumer-property-12fa0ade7b65

Lookup: org.apache.kafka.clients.consumer.ConsumerConfig 

/**
     * <code>session.timeout.ms</code>
     */
    public static final String SESSION_TIMEOUT_MS_CONFIG = "session.timeout.ms";
    private static final String SESSION_TIMEOUT_MS_DOC = "The timeout used to detect consumer failures when using " +
            "Kafka's group management facility. The consumer sends periodic heartbeats to indicate its liveness " +
            "to the broker. If no heartbeats are received by the broker before the expiration of this session timeout, " +
            "then the broker will remove this consumer from the group and initiate a rebalance. Note that the value " +
            "must be in the allowable range as configured in the broker configuration by <code>group.min.session.timeout.ms</code> " +
            "and <code>group.max.session.timeout.ms</code>.";


    public static final String HEARTBEAT_INTERVAL_MS_CONFIG = "heartbeat.interval.ms";
    private static final String HEARTBEAT_INTERVAL_MS_DOC = "The expected time between heartbeats to the consumer " +
            "coordinator when using Kafka's group management facilities. Heartbeats are used to ensure that the " +
            "consumer's session stays active and to facilitate rebalancing when new consumers join or leave the group. " +
            "The value must be set lower than <code>session.timeout.ms</code>, but typically should be set no higher " +
            "than 1/3 of that value. It can be adjusted even lower to control the expected time for normal rebalances.";


    # we would rather have duplicate than missing message
    public static final String AUTO_OFFSET_RESET_CONFIG = "auto.offset.reset";
    public static final String AUTO_OFFSET_RESET_DOC = "What to do when there is no initial offset in Kafka or if the current offset does not exist any more on the server (e.g. because that data has been deleted): <ul><li>earliest: automatically reset the offset to the earliest offset<li>latest: automatically reset the offset to the latest offset</li><li>none: throw exception to the consumer if no previous offset is found for the consumer's group</li><li>anything else: throw exception to the consumer.</li></ul>";


```




All Kafka props
# APACHE KAFKA (KafkaProperties)

```

spring.kafka.admin.client-id= # ID to pass to the server when making requests. Used for server-side logging.
spring.kafka.admin.fail-fast=false # Whether to fail fast if the broker is not available on startup.
spring.kafka.admin.properties.*= # Additional admin-specific properties used to configure the client.
spring.kafka.admin.ssl.key-password= # Password of the private key in the key store file.
spring.kafka.admin.ssl.key-store-location= # Location of the key store file.
spring.kafka.admin.ssl.key-store-password= # Store password for the key store file.
spring.kafka.admin.ssl.key-store-type= # Type of the key store.
spring.kafka.admin.ssl.protocol= # SSL protocol to use.
spring.kafka.admin.ssl.trust-store-location= # Location of the trust store file.
spring.kafka.admin.ssl.trust-store-password= # Store password for the trust store file.
spring.kafka.admin.ssl.trust-store-type= # Type of the trust store.
spring.kafka.bootstrap-servers= # Comma-delimited list of host:port pairs to use for establishing the initial connections to the Kafka cluster. Applies to all components unless overridden.
spring.kafka.client-id= # ID to pass to the server when making requests. Used for server-side logging.
spring.kafka.consumer.auto-commit-interval= # Frequency with which the consumer offsets are auto-committed to Kafka if 'enable.auto.commit' is set to true.
spring.kafka.consumer.auto-offset-reset= # What to do when there is no initial offset in Kafka or if the current offset no longer exists on the server.
spring.kafka.consumer.bootstrap-servers= # Comma-delimited list of host:port pairs to use for establishing the initial connections to the Kafka cluster. Overrides the global property, for consumers.
spring.kafka.consumer.client-id= # ID to pass to the server when making requests. Used for server-side logging.
spring.kafka.consumer.enable-auto-commit= # Whether the consumer's offset is periodically committed in the background.
spring.kafka.consumer.fetch-max-wait= # Maximum amount of time the server blocks before answering the fetch request if there isn't sufficient data to immediately satisfy the requirement given by "fetch-min-size".
spring.kafka.consumer.fetch-min-size= # Minimum amount of data the server should return for a fetch request.
spring.kafka.consumer.group-id= # Unique string that identifies the consumer group to which this consumer belongs.
spring.kafka.consumer.heartbeat-interval= # Expected time between heartbeats to the consumer coordinator.
spring.kafka.consumer.key-deserializer= # Deserializer class for keys.
spring.kafka.consumer.max-poll-records= # Maximum number of records returned in a single call to poll().
spring.kafka.consumer.properties.*= # Additional consumer-specific properties used to configure the client.
spring.kafka.consumer.ssl.key-password= # Password of the private key in the key store file.
spring.kafka.consumer.ssl.key-store-location= # Location of the key store file.
spring.kafka.consumer.ssl.key-store-password= # Store password for the key store file.
spring.kafka.consumer.ssl.key-store-type= # Type of the key store.
spring.kafka.consumer.ssl.protocol= # SSL protocol to use.
spring.kafka.consumer.ssl.trust-store-location= # Location of the trust store file.
spring.kafka.consumer.ssl.trust-store-password= # Store password for the trust store file.
spring.kafka.consumer.ssl.trust-store-type= # Type of the trust store.
spring.kafka.consumer.value-deserializer= # Deserializer class for values.
spring.kafka.jaas.control-flag=required # Control flag for login configuration.
spring.kafka.jaas.enabled=false # Whether to enable JAAS configuration.
spring.kafka.jaas.login-module=com.sun.security.auth.module.Krb5LoginModule # Login module.
spring.kafka.jaas.options= # Additional JAAS options.
spring.kafka.listener.ack-count= # Number of records between offset commits when ackMode is "COUNT" or "COUNT_TIME".
spring.kafka.listener.ack-mode= # Listener AckMode. See the spring-kafka documentation.
spring.kafka.listener.ack-time= # Time between offset commits when ackMode is "TIME" or "COUNT_TIME".
spring.kafka.listener.client-id= # Prefix for the listener's consumer client.id property.
spring.kafka.listener.concurrency= # Number of threads to run in the listener containers.
spring.kafka.listener.idle-event-interval= # Time between publishing idle consumer events (no data received).
spring.kafka.listener.log-container-config= # Whether to log the container configuration during initialization (INFO level).
spring.kafka.listener.monitor-interval= # Time between checks for non-responsive consumers. If a duration suffix is not specified, seconds will be used.
spring.kafka.listener.no-poll-threshold= # Multiplier applied to "pollTimeout" to determine if a consumer is non-responsive.
spring.kafka.listener.poll-timeout= # Timeout to use when polling the consumer.
spring.kafka.listener.type=single # Listener type.
spring.kafka.producer.acks= # Number of acknowledgments the producer requires the leader to have received before considering a request complete.
spring.kafka.producer.batch-size= # Default batch size.
spring.kafka.producer.bootstrap-servers= # Comma-delimited list of host:port pairs to use for establishing the initial connections to the Kafka cluster. Overrides the global property, for producers.
spring.kafka.producer.buffer-memory= # Total memory size the producer can use to buffer records waiting to be sent to the server.
spring.kafka.producer.client-id= # ID to pass to the server when making requests. Used for server-side logging.
spring.kafka.producer.compression-type= # Compression type for all data generated by the producer.
spring.kafka.producer.key-serializer= # Serializer class for keys.
spring.kafka.producer.properties.*= # Additional producer-specific properties used to configure the client.
spring.kafka.producer.retries= # When greater than zero, enables retrying of failed sends.
spring.kafka.producer.ssl.key-password= # Password of the private key in the key store file.
spring.kafka.producer.ssl.key-store-location= # Location of the key store file.
spring.kafka.producer.ssl.key-store-password= # Store password for the key store file.
spring.kafka.producer.ssl.key-store-type= # Type of the key store.
spring.kafka.producer.ssl.protocol= # SSL protocol to use.
spring.kafka.producer.ssl.trust-store-location= # Location of the trust store file.
spring.kafka.producer.ssl.trust-store-password= # Store password for the trust store file.
spring.kafka.producer.ssl.trust-store-type= # Type of the trust store.
spring.kafka.producer.transaction-id-prefix= # When non empty, enables transaction support for producer.
spring.kafka.producer.value-serializer= # Serializer class for values.
spring.kafka.properties.*= # Additional properties, common to producers and consumers, used to configure the client.
spring.kafka.ssl.key-password= # Password of the private key in the key store file.
spring.kafka.ssl.key-store-location= # Location of the key store file.
spring.kafka.ssl.key-store-password= # Store password for the key store file.
spring.kafka.ssl.key-store-type= # Type of the key store.
spring.kafka.ssl.protocol= # SSL protocol to use.
spring.kafka.ssl.trust-store-location= # Location of the trust store file.
spring.kafka.ssl.trust-store-password= # Store password for the trust store file.
spring.kafka.ssl.trust-store-type= # Type of the trust store.
spring.kafka.streams.application-id= # Kafka streams application.id property; default spring.application.name.
spring.kafka.streams.auto-startup=true # Whether or not to auto-start the streams factory bean.
spring.kafka.streams.bootstrap-servers= # Comma-delimited list of host:port pairs to use for establishing the initial connections to the Kafka cluster. Overrides the global property, for streams.
spring.kafka.streams.cache-max-size-buffering= # Maximum memory size to be used for buffering across all threads.
spring.kafka.streams.client-id= # ID to pass to the server when making requests. Used for server-side logging.
spring.kafka.streams.properties.*= # Additional Kafka properties used to configure the streams.
spring.kafka.streams.replication-factor= # The replication factor for change log topics and repartition topics created by the stream processing application.
spring.kafka.streams.ssl.key-password= # Password of the private key in the key store file.
spring.kafka.streams.ssl.key-store-location= # Location of the key store file.
spring.kafka.streams.ssl.key-store-password= # Store password for the key store file.
spring.kafka.streams.ssl.key-store-type= # Type of the key store.
spring.kafka.streams.ssl.protocol= # SSL protocol to use.
spring.kafka.streams.ssl.trust-store-location= # Location of the trust store file.
spring.kafka.streams.ssl.trust-store-password= # Store password for the trust store file.
spring.kafka.streams.ssl.trust-store-type= # Type of the trust store.
spring.kafka.streams.state-dir= # Directory location for the state store.
spring.kafka.template.default-topic= # Default topic to which messages are sent.

```

