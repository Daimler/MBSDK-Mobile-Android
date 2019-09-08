import com.daimler.rs.VaultConfig
import com.daimler.rs.VaultParameterSpec

def NODE = 'prbuild'

def GIT_URL = 'https://github.com/Daimler/MBSDK-Mobile-Android.git'
def MODULE_NAME = 'mbmobilesdk'
def CONFIG = 'release'

def approle = 'RIS_VAULT_APPROLE'

String pr = env.BRANCH_NAME

node(NODE) {
    if (!pr.startsWith('PR-')) {
        return
    }

    def branch = scm.branches

    echo "Checking out PR $pr on branch $branch"

    def mavenUserSpec = new VaultParameterSpec('app/ris/frontend/artifactory/user', 'user', 'MAVEN_USER')
    def mavenPwSpec = new VaultParameterSpec('app/ris/frontend/artifactory/pw', 'pw', 'MAVEN_PW')
    def ldKeySpecDev = new VaultParameterSpec('app/ris/frontend/ld/dev', 'apiKey', 'LD_API_DEV')
    def ldKeySpecProd = new VaultParameterSpec('app/ris/frontend/ld/prod', 'apiKey', 'LD_API_PROD')
    def jumioApiToken = new VaultParameterSpec('app/ris/frontend/jumio/apiToken', 'apiToken', 'JUMIO_API_TOKEN')
    def jumioApiSecret = new VaultParameterSpec('app/ris/frontend/jumio/apiSecret', 'apiSecret', 'JUMIO_API_SECRET')
    def inboxApiKey = new VaultParameterSpec('app/ris/frontend/inbox/api', 'apiKey', 'INBOX_API_KEY')
    def vaultConfig = new VaultConfig(approle, 'local.properties', null, null, [mavenUserSpec, mavenPwSpec, ldKeySpecDev, ldKeySpecProd, jumioApiToken, jumioApiSecret, inboxApiKey])

    androidPipeline.doPrLibraryBuild(GIT_URL, MODULE_NAME, CONFIG, branch, vaultConfig)
}