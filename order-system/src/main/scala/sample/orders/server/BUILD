scala_binary(
    name = "server",
    srcs = glob(["*.scala"]),
    deps = [
        "@mysql__mysql_connector_java//jar",
        "@jsr311_api//jar",
        "@jetty_server//jar",
        "@jetty_util//jar",
        "@jetty_http//jar",
        "@jetty_io//jar",
        "@jetty_servlet//jar",
        "@javax_servlet_api//jar",
        "//order-system/src/main/scala/sample/orders/common",
        "//order-system/src/main/scala/sample/orders/dal",
        "//order-system/src/main/scala/sample/orders/core",
    ],
    main_class = "sample.orders.server.OrderSystemApp"
)
