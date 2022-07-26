package usecase

import com.example.gamesuitandroid.enumm.SuitCharacter

class SuitUseCaseImpl {
    companion object{
        const val SERI = 0
        const val PEMAIN_SATU_MENANG = 1
        const val PEMAIN_DUA_MENANG = 2
    }
    fun decideWinner(pemainSatu: Int, pemainDua: Int) : Int {
        return if(pemainSatu == pemainDua){
            SERI
        }else if(pemainSatu == SuitCharacter.BATU.ordinal){
            if(pemainDua == SuitCharacter.KERTAS.ordinal){
                PEMAIN_DUA_MENANG
            }else{
                PEMAIN_SATU_MENANG
            }
        }else if(pemainSatu == SuitCharacter.KERTAS.ordinal){
            if(pemainDua == SuitCharacter.GUNTING.ordinal){
                PEMAIN_DUA_MENANG
            }else{
                PEMAIN_SATU_MENANG
            }
        }else{
            //if player 1 pick scissor
            if(pemainDua == SuitCharacter.BATU.ordinal){
                PEMAIN_DUA_MENANG
            }else{
                PEMAIN_SATU_MENANG
            }
        }
    }

}