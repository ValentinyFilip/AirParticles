package editList

import ParticlesItem
import addParticle
import emotion.react.css
import getParticles
import hex
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface EditProps: Props {
    var particlesList: List<ParticlesItem>
    var selectedItem: ParticlesItem?
    var onChange: (List<ParticlesItem>) -> Unit
}

private val scope = MainScope()

var edit = FC<EditProps> { props ->
    div {

        css{
            backgroundColor = hex("#E7E9EB")
            hover {
                backgroundColor = hex("#CCCCCC")
            }
        }
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