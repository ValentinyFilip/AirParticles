package editList

import ParticlesItem
import addParticle
import csstype.px
import deleteParticle
import emotion.react.css
import getParticles
import hex
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.div
import showAll.showAll

external interface EditProps: Props {
    var particlesList: List<ParticlesItem>
    var selectedItem: ParticlesItem?
    var onSelectedItem: (ParticlesItem) -> Unit
    var onChange: (List<ParticlesItem>) -> Unit
}

private val scope = MainScope()

var edit = FC<EditProps> { props ->
    div {
        css {
            backgroundColor = hex("#E7E9EB")
            hover {
                backgroundColor = hex("#CCCCCC")
            }
        }
        showAll {
            edit = true
            particlesList = props.particlesList
            selectedItem = props.selectedItem
            onSelectedItem = props.onSelectedItem
            onDelete = { item ->
                scope.launch {
                    deleteParticle(item)
                    props.onChange(getParticles())
                }
            }
        }
    }
    div {
        css {
            width = 200.px
        }
        InputComponent {
            onSubmit = { input ->
                val sensor = ParticlesItem(0, 0, input)
                scope.launch {
                    addParticle(sensor)
                    props.onChange(getParticles())
                }
            }
        }
    }
}