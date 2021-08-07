import {
    ElRow,
    ElCol,
    ElButton,
    ElContainer,
    ElHeader,
    ElMain,
    ElFooter,
    ElMenu,
    ElMenuItem,
    ElForm,
    ElFormItem,
    ElInput,
    ElSelect,
    ElOption,
    ElRadioGroup,
    ElRadio,
    ElDatePicker,
    ElUpload,
    ElDivider
} from 'element-plus'

export default (app) => {
    app.use(ElRow)
    app.use(ElCol)
    app.use(ElButton)
    app.use(ElContainer)
    app.use(ElHeader)
    app.use(ElMain)
    app.use(ElFooter)
    app.use(ElMenu)
    app.use(ElMenuItem)
    app.use(ElForm)
    app.use(ElFormItem)
    app.use(ElInput)
    app.use(ElSelect)
    app.use(ElOption)
    app.use(ElRadioGroup)
    app.use(ElRadio)
    app.use(ElDatePicker)
    app.use(ElUpload)
    app.use(ElDivider)
}
