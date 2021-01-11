package contracts.person

import org.springframework.cloud.contract.spec.Contract

Contract.make {
	description (
		"""
        """
	)
	request {
		method 'POST'
		url '/qunar/hello'
		body(
			id: 1,
			name: "Labniz"
		)
		headers {
			contentType(applicationJson())
		}
	}
	response {
		status 200
		body(
				id: 2,
				name: "Hawking"
		)
		headers {
			contentType(applicationJson())
		}
	}
}