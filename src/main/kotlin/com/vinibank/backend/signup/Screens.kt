package com.vinibank.backend.signup

import com.vinibank.backend.model.ActionModel
import com.vinibank.backend.model.ComponentModel
import com.vinibank.backend.model.PropertyModel
import com.vinibank.backend.model.ScreenModel
import com.vinibank.backend.model.ValidatorModel

fun emailScreen(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "Email",
    version = "1",
    template = "",
    shouldCache = false,
    components = listOf(
        ComponentModel(
            type = "topAppBar",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Email")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.Email.emailInput"),
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Digite seu email",
                "isError" to "false",
                "errorMessage" to "Email já cadastrado",
            ),
            validators = listOf(
                ValidatorModel(
                    type = "emailValid",
                    id = "SignUp.Email.isEmailValid",
                    required = listOf(
                        "SignUp.Email.emailInput",
                    ),
                )
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Bottom",
            ),
            components = listOf(
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Continuar"),
                        PropertyModel(
                            name = "enabled",
                            value = "false",
                            id = "SignUp.Email.isEmailValid"
                        ),
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "continue",
                        data = mapOf(
                            "flowId" to "SignUp",
                            "nextScreenId" to "PersonalInfo",
                            "currentScreenId" to "Email",
                            "screenRequestData" to """{ "SignUp.Email.emailInput" : "email" }""",
                            "screenData" to screenData
                        ),
                    )
                ),
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Fechar")
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "close"
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        )
    )
)

fun emailScreenError(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "Email",
    version = "1",
    template = "",
    shouldCache = false,
    components = listOf(
        ComponentModel(
            type = "topAppBar",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Email")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.Email.emailInput"),
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Digite seu email",
                "isError" to "true",
                "errorMessage" to "Email já cadastrado",
            ),
            validators = listOf(
                ValidatorModel(
                    type = "emailValid",
                    id = "SignUp.Email.isEmailValid",
                    required = listOf(
                        "SignUp.Email.emailInput",
                    ),
                )
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Bottom",
            ),
            components = listOf(
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Continuar"),
                        PropertyModel(
                            name = "enabled",
                            value = "false",
                            id = "SignUp.Email.isEmailValid"
                        ),
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "continue",
                        data = mapOf(
                            "flowId" to "SignUp",
                            "nextScreenId" to "PersonalInfo",
                            "currentScreenId" to "Email",
                            "screenRequestData" to """{ "SignUp.Email.emailInput" : "email" }""",
                            "screenData" to screenData
                        ),
                    )
                ),
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Fechar")
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "close"
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        )
    )
)

fun personalInfoScreen(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "PersonalInfo",
    version = "1",
    template = "",
    shouldCache = false,
    components = listOf(
        ComponentModel(
            type = "topAppBar",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Informações Pessoais")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.nameInput")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Nome completo",
            ),
            validators = listOf(
                ValidatorModel(
                    type = "minLength",
                    data = mapOf("length" to "3"),
                    id = "SignUp.PersonalInfo.isNameFilled",
                    required = listOf(
                        "SignUp.PersonalInfo.nameInput",
                    ),
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.documentInput")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "CPF",
                "textFormatter" to "Documento.CPF"
            ),
            validators = listOf(
                ValidatorModel(
                    type = "minLength",
                    id = "SignUp.PersonalInfo.isCpfValid",
                    data = mapOf("length" to "11"),
                    required = listOf(
                        "SignUp.PersonalInfo.documentInput",
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "textInput",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "", id = "SignUp.PersonalInfo.phoneInput")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "label" to "Telefone",
                "textFormatter" to "Telefone",
            ),
            validators = listOf(
                ValidatorModel(
                    type = "minLength",
                    data = mapOf("length" to "11"),
                    id = "SignUp.PersonalInfo.isPhoneFilled",
                    required = listOf(
                        "SignUp.PersonalInfo.phoneInput",
                    ),
                )
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Bottom",
            ),
            components = listOf(
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Continuar"),
                        PropertyModel(
                            name = "enabled",
                            value = "false",
                            id = "SignUp.PersonalInfo.continueButton"
                        ),
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "continue",
                        data = mapOf(
                            "flowId" to "SignUp",
                            "nextScreenId" to "Password",
                            "currentScreenId" to "PersonalInfo",
                            "screenRequestData" to """{ 
                                "SignUp.PersonalInfo.nameInput" : "name",
                                "SignUp.PersonalInfo.documentInput" : "document", 
                                "SignUp.PersonalInfo.phoneInput" : "phone" 
                                }""".trimMargin(),
                            "screenData" to screenData
                        ),
                    ),
                    validators = listOf(
                        ValidatorModel(
                            type = "allTrue",
                            id = "SignUp.PersonalInfo.continueButton",
                            required = listOf(
                                "SignUp.PersonalInfo.isNameFilled",
                                "SignUp.PersonalInfo.isCpfValid",
                                "SignUp.PersonalInfo.isPhoneFilled",
                            ),
                        )
                    )
                ),
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Voltar")
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "back",
                        data = mapOf()
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        )
    )
)

fun passwordScreen(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "Password",
    version = "1",
    template = "",
    shouldCache = false,
    components = listOf(
        ComponentModel(
            type = "topAppBar",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Criar Senha")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        ),
        ComponentModel(
            type = "createPassword",
            dynamicProperty = listOf(
                PropertyModel(
                    name = "isPasswordValid",
                    value = "false",
                    id = "SignUp.PasswordScreen.isPasswordValid"
                ),
                PropertyModel(
                    name = "text",
                    value = "",
                    id = "SignUp.PasswordScreen.passwordInput"
                ),
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Bottom",
            ),
            components = listOf(
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Continuar"),
                        PropertyModel(
                            name = "enabled",
                            value = "false",
                            id = "SignUp.PasswordScreen.isPasswordValid"
                        ),
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "continue",
                        data = mapOf(
                            "flowId" to "SignUp",
                            "nextScreenId" to "Success",
                            "currentScreenId" to "Password",
                            "screenRequestData" to """{ "SignUp.PasswordScreen.passwordInput" : "password" }""",
                            "screenData" to screenData
                        ),
                    )
                ),
                ComponentModel(
                    type = "button",
                    dynamicProperty = listOf(
                        PropertyModel(name = "text", value = "Voltar")
                    ),
                    staticProperty = mapOf(
                        "horizontalFillType" to "Max"
                    ),
                    action = ActionModel(
                        type = "back"
                    )
                )
            )
        ),
        ComponentModel(
            type = "spacer",
            staticProperty = mapOf(
                "size" to "20"
            )
        )
    )
)

fun successScreen(screenData: String) = ScreenModel(
    flow = "SignUp",
    stage = "Success",
    version = "1",
    template = "",
    shouldCache = true,
    components = listOf(
        ComponentModel(
            type = "topAppBar",
            dynamicProperty = listOf(
                PropertyModel(name = "text", value = "Conta Criada com Sucesso")
            ),
            staticProperty = mapOf(
                "horizontalFillType" to "Max",
                "paddingHorizontal" to "20",
                "textAlign" to "Center",
            )
        ),
        ComponentModel(
            type = "column",
            staticProperty = mapOf(
                "horizontalAlignment" to "Center",
                "paddingHorizontal" to "20",
                "horizontalFillType" to "Max",
                "weight" to "1",
                "verticalArrangement" to "Center",
            ),
            components = listOf(
                ComponentModel(
                    type = "lottie",
                    dynamicProperty = listOf(),
                    staticProperty = mapOf(
                        "animation" to """{"v":"4.8.0","meta":{"g":"LottieFiles AE 1.0.0","a":"","k":"","d":"","tc":""},"fr":60,"ip":0,"op":130,"w":512,"h":512,"nm":"HDFC Success","ddd":0,"assets":[],"layers":[{"ddd":0,"ind":1,"ty":4,"nm":"check","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[256,256,0],"ix":2},"a":{"a":0,"k":[0,0,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ind":0,"ty":"sh","ix":1,"ks":{"a":0,"k":{"i":[[0,0],[0,0],[0,0]],"o":[[0,0],[0,0],[0,0]],"v":[[-82.5,4.5],[-31,55],[73,-52.5]],"c":false},"ix":2},"nm":"Path 1","mn":"ADBE Vector Shape - Group","hd":false},{"ty":"tm","s":{"a":0,"k":0,"ix":1},"e":{"a":1,"k":[{"i":{"x":[0.667],"y":[1]},"o":{"x":[1],"y":[0.076]},"t":60,"s":[0]},{"t":85,"s":[100]}],"ix":2},"o":{"a":0,"k":0,"ix":3},"m":1,"ix":2,"nm":"Trim Paths 1","mn":"ADBE Vector Filter - Trim","hd":false},{"ty":"st","c":{"a":0,"k":[1,1,1,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":30,"ix":5},"lc":2,"lj":2,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"tr","p":{"a":0,"k":[0,0],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Shape 1","np":4,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":240,"st":0,"bm":0},{"ddd":0,"ind":3,"ty":4,"nm":"Shape Layer 2","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[256,257.86,0],"ix":2},"a":{"a":0,"k":[0,0,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0,0,0.833],"y":[0.98,0.98,-66.114]},"o":{"x":[0.656,0.656,0.167],"y":[0.872,0.872,67.114]},"t":20,"s":[0,0,100]},{"t":60,"s":[150,150,100]}],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"d":1,"ty":"el","s":{"a":0,"k":[236,236],"ix":2},"p":{"a":0,"k":[0,0],"ix":3},"nm":"Ellipse Path 1","mn":"ADBE Vector Shape - Ellipse","hd":false},{"ty":"fl","c":{"a":0,"k":[0.172549019608,0.854901960784,0.580392156863,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[0,-3],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Ellipse 1","np":3,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":240,"st":0,"bm":0},{"ddd":0,"ind":4,"ty":4,"nm":"Shape Layer 1","sr":1,"ks":{"o":{"a":1,"k":[{"i":{"x":[0.626],"y":[0.729]},"o":{"x":[0.912],"y":[0.073]},"t":76,"s":[100]},{"t":119,"s":[0]}],"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[256,257.86,0],"ix":2},"a":{"a":0,"k":[0,0,0],"ix":1},"s":{"a":1,"k":[{"i":{"x":[0,0,0.667],"y":[0.999,0.999,1]},"o":{"x":[0.477,0.477,0.333],"y":[0.587,0.587,0]},"t":10,"s":[0,0,100]},{"i":{"x":[0.833,0.833,0.833],"y":[1,1,1]},"o":{"x":[0.167,0.167,0.167],"y":[0,0,0]},"t":50,"s":[150,150,100]},{"i":{"x":[0.833,0.833,0.833],"y":[1,1,1]},"o":{"x":[0.167,0.167,0.167],"y":[0,0,0]},"t":76,"s":[150,150,100]},{"t":123,"s":[210,210,100]}],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"d":1,"ty":"el","s":{"a":0,"k":[236,236],"ix":2},"p":{"a":0,"k":[0,0],"ix":3},"nm":"Ellipse Path 1","mn":"ADBE Vector Shape - Ellipse","hd":false},{"ty":"st","c":{"a":0,"k":[1,1,1,1],"ix":3},"o":{"a":0,"k":100,"ix":4},"w":{"a":0,"k":2,"ix":5},"lc":1,"lj":1,"ml":4,"bm":0,"nm":"Stroke 1","mn":"ADBE Vector Graphic - Stroke","hd":false},{"ty":"fl","c":{"a":0,"k":[0.783504889993,0.945098039216,0.880089314779,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[0,-3],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Ellipse 1","np":3,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":240,"st":0,"bm":0},{"ddd":0,"ind":5,"ty":4,"nm":"BG","sr":1,"ks":{"o":{"a":0,"k":100,"ix":11},"r":{"a":0,"k":0,"ix":10},"p":{"a":0,"k":[256,256,0],"ix":2},"a":{"a":0,"k":[0,0,0],"ix":1},"s":{"a":0,"k":[100,100,100],"ix":6}},"ao":0,"shapes":[{"ty":"gr","it":[{"ty":"rc","d":1,"s":{"a":0,"k":[554,556],"ix":2},"p":{"a":0,"k":[0,0],"ix":3},"r":{"a":0,"k":0,"ix":4},"nm":"Rectangle Path 1","mn":"ADBE Vector Shape - Rect","hd":false},{"ty":"fl","c":{"a":0,"k":[1,1,1,1],"ix":4},"o":{"a":0,"k":100,"ix":5},"r":1,"bm":0,"nm":"Fill 1","mn":"ADBE Vector Graphic - Fill","hd":false},{"ty":"tr","p":{"a":0,"k":[-1,6],"ix":2},"a":{"a":0,"k":[0,0],"ix":1},"s":{"a":0,"k":[100,100],"ix":3},"r":{"a":0,"k":0,"ix":6},"o":{"a":0,"k":100,"ix":7},"sk":{"a":0,"k":0,"ix":4},"sa":{"a":0,"k":0,"ix":5},"nm":"Transform"}],"nm":"Rectangle 1","np":3,"cix":2,"bm":0,"ix":1,"mn":"ADBE Vector Group","hd":false}],"ip":0,"op":240,"st":0,"bm":0}],"markers":[]}"""
                    ),
                    action = ActionModel(
                        type = "businessSuccess",
                        data = mapOf(
                            "screenData" to screenData
                        ),
                    )
                )
            )
        )
    )
)