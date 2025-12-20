package com.mnessim.researchtrackerkmp.domain.repositories

import com.mnessim.Database
import com.mnessim.Terms
import com.mnessim.researchtrackerkmp.domain.models.Term

class TermsRepo(private val database: Database) {
    private val queries = database.termsDatabaseQueries

    fun getAllTerms(): List<Term> {
        return queries.selectAll().executeAsList()
            .map { rowToTerm(it) }
    }

    fun getTermById(id: Long): Term? {
        val row = queries.selectOne(id).executeAsOneOrNull() ?: return null
        return rowToTerm(row)
    }

    fun insertTerm(term: String, locked: Boolean) {
        queries.insertTerm(term, locked)
    }

    fun updateTerm(term: Term) {
        queries.updateTerm(
            term = term.term,
            locked = term.locked,
            id = term.id
        )
    }

    fun deleteTerm(id: Long) {
        queries.deleteTerm(id)
    }

    private fun rowToTerm(row: Terms): Term {
        return Term(id = row.id, term = row.term, locked = row.locked ?: false)
    }
}