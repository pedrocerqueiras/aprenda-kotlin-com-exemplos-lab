// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

import kotlin.random.Random

enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class Aluno (val nome: String)

data class Disciplina (var nome: String, val nivel: Nivel, val duracao: Int = 60)

data class Curso (val nome: String, var conteudos: List<Disciplina>, val duracaoTotal: Int) {

    val matriculados = mutableListOf<Aluno>()
	val alunosDisciplinas = mutableMapOf<Aluno, List<Disciplina>>()
    
    fun matricular(aluno: Aluno) {
        matriculados.add(aluno)
    }
    
    fun matricularEmDisciplinas(aluno: Aluno) {
        val numeroDeDisciplinas = Random.nextInt(1, 4)
        val disciplinasAleatorias = conteudos.shuffled().take(numeroDeDisciplinas)
        alunosDisciplinas[aluno] = disciplinasAleatorias
    }
    
}

fun main() {
    
    val disciplinasMobile = listOf(
        Disciplina("Introdução à Programação", Nivel.BASICO, 90), 
        Disciplina("Estruturas de Dados", Nivel.INTERMEDIARIO, 120),
        Disciplina("Introdução à linguagem Kotlin", Nivel.AVANCADO, 90)
    )
   
    val cursoMobile = Curso("Programação Mobile", disciplinasMobile, 300)
    
    val disciplinasWeb = listOf(
        Disciplina("HTML e CSS", Nivel.BASICO, 60),
        Disciplina("JavaScript", Nivel.INTERMEDIARIO, 120),
        Disciplina("Backend com Node.js", Nivel.AVANCADO, 150)
    )
    
    val cursoWeb = Curso("Desenvolvimento Web", disciplinasWeb, 330)

    val alunos = listOf(
        Aluno("Pedro"),
        Aluno("Antonio"),
        Aluno("Maria"),
        Aluno("João"),
        Aluno("Carla")
    )

   val cursos = listOf(cursoMobile, cursoWeb)

    for (aluno in alunos) {
        val cursoAleatorio = cursos[Random.nextInt(cursos.size)]
        cursoAleatorio.matricular(aluno)
        cursoAleatorio.matricularEmDisciplinas(aluno)
    }

   for (curso in cursos) {
        println("Curso: ${curso.nome}")
        for ((aluno, disciplinas) in curso.alunosDisciplinas) {
            val disciplinasInfo = disciplinas.joinToString(", ") {
                "${it.nome} (${it.nivel} - ${it.duracao} minutos)"
            }
            println(" - Aluno: ${aluno.nome}, Disciplinas: $disciplinasInfo")
        }
        println()
    }
}