$version: "2"

namespace com.principate.delphi.api

use alloy#simpleRestJson

@simpleRestJson
service CampaignService {
    version: "0.1.0-SNAPSHOT"
    operations: [HealthCheck]
}

@readonly
@http(method: "GET", uri: "/health", code: 200)
operation HealthCheck {
    output := {
        status: String = "Still alive..."
    }
}
