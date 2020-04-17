package dev.claucookielabs.pasbuk.common.data.repository.mappers

import dev.claucookielabs.pasbuk.common.data.datasource.local.model.*
import dev.claucookielabs.pasbuk.common.domain.model.*
import dev.claucookielabs.pasbuk.common.presentation.utils.RGBColorParser

fun DbPassbook.toDomain(): Passbook {
    val colorParser = RGBColorParser()
    return Passbook(
        formatVersion = this.formatVersion,
        serialNumber = this.serialNumber,
        passTypeIdentifier = this.passTypeIdentifier,
        teamIdentifier = this.teamIdentifier,
        authenticationToken = this.authenticationToken,
        webServiceURL = this.webServiceURL,
        organizationName = this.organizationName,
        description = this.description,
        barcode = this.barcode.toDomain(),
        location = this.location.toDomain(),
        maxDistance = this.maxDistance,
        relevantDate = this.relevantDate,
        updateDate = this.updateDate,
        backgroundColor = colorParser.parseAndGetDarkerColor(
            this.backgroundColor,
            COLOR_BACKGROUND_LIGHTER_FACTOR,
            false
        ),
        foregroundColor = colorParser.parseAndGetDarkerColor(
            this.foregroundColor,
            COLOR_LABEL_DARKER_FACTOR,
            false
        ),
        labelColor = colorParser.parseAndGetDarkerColor(
            this.labelColor,
            COLOR_LABEL_DARKER_FACTOR,
            true
        ),
        logoText = this.logoText,
        pass = this.pass.toDomain(),
        logoImage = this.logoImage,
        backgroundImage = this.backgroundImage,
        stripImage = this.stripImage,
        thumbnailImage = this.thumbnailImage,
        pkpassFile = this.pkpassFile,
        iconImage = null,
        footerImage = null
    )
}


fun DbPass?.toDomain(): Pass {
    return Pass(
        headerFields = this?.headerFields.toDomain(),
        primaryFields = this?.primaryFields.toDomain(),
        secondaryFields = this?.secondaryFields.toDomain(),
        backFields = this?.backFields.toDomain(),
        auxiliaryFields = this?.auxiliaryFields.toDomain(),
        transitType = TransitType.fromName(this?.transitType),
        passType = PassType.fromName(this?.passType)
    )
}

fun DbLocation?.toDomain(): Location {
    return this?.run {
        Location(
            latitude = this.latitude,
            longitude = this.longitude,
            relevantText = this.locationRelevantText
        )
    } ?: EmptyLocation()
}

fun DbBarcode?.toDomain(): Barcode {
    return this?.run {
        Barcode(
            format = this.barcodeFormat,
            message = this.barcodeMessage,
            messageEncoding = this.messageEncoding,
            altText = this.altText
        )
    } ?: Barcode.EMPTY
}


fun List<DbInfoField>?.toDomain(): List<InfoField> {
    return this?.let { infoFields ->
        return infoFields.map {
            InfoField(
                key = it.key,
                value = it.value,
                label = it.label,
                currencyCode = it.currencyCode,
                attributedValue = it.attributedValue,
                changeMessage = it.changeMessage,
                dataDetectorTypes = it.dataDetectorTypes ?: emptyList(),
                textAlignment = TextAlignment.fromName(it.textAlignment),
                dateStyle = DateStyle.fromName(it.dateStyle),
                timeStyle = DateStyle.fromName(it.dateStyle)
            )
        }
    } ?: listOf(InfoField.EMPTY)
}


const val COLOR_LABEL_DARKER_FACTOR = -0.4f
const val COLOR_BACKGROUND_LIGHTER_FACTOR = 0.10f
