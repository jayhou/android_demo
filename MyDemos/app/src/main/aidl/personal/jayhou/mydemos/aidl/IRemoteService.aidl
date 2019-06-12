// IRemoteService.aidl
package personal.jayhou.mydemos.aidl;
import personal.jayhou.mydemos.aidl.ICallback;

interface IRemoteService {
    void callbackTrans(ICallback callback);
}
